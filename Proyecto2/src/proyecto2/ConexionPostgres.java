package proyecto2;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

/** Conexiones configurables; nunca deja una conexión nula tras un fallo. */
public class ConexionPostgres implements AutoCloseable {
    private final Connection conn;

    public ConexionPostgres() throws ClassNotFoundException, SQLException {
        this(env("VET_DATABASE", "dbProyecto"), env("VET_USER", "postgres"), env("VET_PASSWORD", ""));
    }

    public ConexionPostgres(String nombreDB, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = env("VET_JDBC_URL", "jdbc:postgresql://127.0.0.1:5432/" + nombreDB);
        java.util.Properties propiedades = new java.util.Properties();
        propiedades.setProperty("user", env("VET_USER", user));
        propiedades.setProperty("password", env("VET_PASSWORD", password));
        propiedades.setProperty("connectTimeout", "5");
        this.conn = DriverManager.getConnection(url, propiedades);
    }

    public ConexionPostgres(Connection connection) {
        this.conn = java.util.Objects.requireNonNull(connection);
    }

    private static String env(String key, String fallback) {
        String value = System.getenv(key);
        return value == null ? fallback : value;
    }

    public ResultSet query(String sql, Object... parameters) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            bind(statement, parameters);
            try (ResultSet rows = statement.executeQuery()) {
                CachedRowSet result = RowSetProvider.newFactory().createCachedRowSet();
                result.populate(rows);
                return result;
            }
        }
    }

    public int execute(String sql, Object... parameters) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            bind(statement, parameters);
            return statement.executeUpdate();
        }
    }

    private static void bind(PreparedStatement statement, Object[] parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) statement.setObject(i + 1, parameters[i]);
    }

    // Legacy fragments remain for compatibility with the original forms.
    // Do not expose these methods to untrusted SQL or an external service.
    public ResultSet select(String campos, String tabla, String condicion) {
        try { return query("SELECT " + campos + " FROM " + tabla + (condicion == null || condicion.trim().isEmpty() ? "" : " " + condicion.trim())); }
        catch (SQLException error) { throw new IllegalStateException("No se pudo consultar la base de datos.", error); }
    }

    public String insertar(String tabla, String valores) { return mutate("INSERT INTO " + tabla + " VALUES " + valores, "Datos Insertados"); }
    public String modificar(String tabla, String valores) { return mutate("UPDATE " + tabla + " " + valores, "Datos Modificados"); }
    public String borrar(String tabla, String valores) { return mutate("DELETE FROM " + tabla + " " + valores, "Datos Eliminados"); }
    private String mutate(String sql, String result) {
        try { int count = execute(sql); return count > 0 ? result : "No se modificaron registros"; }
        catch (SQLException error) { return "No se pudo modificar la base de datos (SQLState " + error.getSQLState() + ")"; }
    }

    @Override public void close() throws SQLException { conn.close(); }
}
