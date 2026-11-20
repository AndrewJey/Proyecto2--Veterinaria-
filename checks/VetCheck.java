public class VetCheck {
 public static void main(String[] args) throws Exception {

 Class.forName("org.postgresql.Driver");CheckSupport.check(java.sql.DriverManager.getDriver("jdbc:postgresql://127.0.0.1/test")!=null,"Driver available");
 try{new proyecto2.ConexionPostgres((java.sql.Connection)null);throw new AssertionError();}catch(NullPointerException expected){}
 if(System.getenv("VET_TEST_DATABASE")!=null){
  try(proyecto2.ConexionPostgres db=new proyecto2.ConexionPostgres()){
   db.execute("CREATE TEMP TABLE vet_check(id int primary key, name text)");
   CheckSupport.equal(db.execute("INSERT INTO vet_check VALUES (?,?)",1,"O'Brien áé"),1);
   try(java.sql.ResultSet found=db.query("SELECT name FROM vet_check WHERE id=?",1)){CheckSupport.check(found.next(),"Query row");CheckSupport.equal(found.getString(1),"O'Brien áé");}
   CheckSupport.check(db.insertar("vet_check","(2,'Second')").contains("Insertados"),"Legacy INSERT uses update");
   try(java.sql.ResultSet found=db.select("*","vet_check","WHERE id=2")){CheckSupport.check(found.next(),"Legacy where respected");CheckSupport.equal(found.getInt(1),2);CheckSupport.check(!found.next(),"Filtered row only");}
   CheckSupport.check(db.modificar("vet_check","SET name='Updated' WHERE id=2").contains("Modificados"),"Legacy UPDATE");
   CheckSupport.check(db.borrar("vet_check","WHERE id=2").contains("Eliminados"),"Legacy DELETE");
   CheckSupport.equal(db.execute("DELETE FROM vet_check"),1);
  }
 }else System.out.println("Database integration not run: set VET_TEST_DATABASE and VET connection variables against a disposable database.");

 CheckSupport.done();
 }
}
