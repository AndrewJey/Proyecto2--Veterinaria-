

<!-- local-development:start -->
# Desarrollo local

Puedes abrir este repositorio en VS Code, Antigravity o Sublime Text. El compilador y los servicios necesarios dependen del componente indicado abajo.

## Java / NetBeans

En NetBeans 25, abre la carpeta que contiene `build.xml` como proyecto Ant. Selecciona un JDK igual o posterior al nivel de fuente del proyecto; las compilaciones de esta revisión usan JDK 24.0.2. El asistente busca NetBeans y el JDK y mantiene la configuración de usuario en `.local-build/`.

```powershell
..\build-local.ps1
.\build-local.ps1 -Project 'Proyecto2/build.xml' -Target run
```

Si hay varios proyectos, elige el deseado con `-Project`. Puedes indicar `-JavaHome` y `-NetBeansHome`. `-Target test` ejecuta las pruebas que declare el proyecto; un objetivo de pruebas vacío no acredita cobertura.

## Componentes y validación

Inventario y compilaciones locales del 2026-11-15. Una compilación correcta no acredita todavía interacción, compatibilidad de base de datos, instalador ni despliegue.

| Componente | Tipo / destino | Validación |
| --- | --- | --- |
| [Proyecto2/build.xml](<Proyecto2/build.xml>) | netbeans-ant / 1.8 | Compila y pasa verificación funcional (`checks/`) |

## Archivos ignorados

`.gitignore` excluye credenciales locales, estado del editor y salidas de las herramientas detectadas. No deja de versionar archivos que ya estaban en Git. Los manifiestos, archivos de bloqueo, recursos fuente y scripts de esquema forman parte del código.
<!-- local-development:end -->

<!-- improvements-20261119:start -->
## Mejoras y verificación funcional (2026-11-19)

- **Mejoras aplicadas:** Actualizado el driver PostgreSQL JDBC a `42.7.14` (compatible con SCRAM-SHA-256 en PostgreSQL 17 y 18), configurabilidad por variables de entorno (`VET_JDBC_URL`, `VET_DATABASE`, `VET_USER`, `VET_PASSWORD`), consultas parametrizadas con `AutoCloseable`/`CachedRowSet` y corrección de llamadas CRUD en los formularios.
- **Pruebas ejecutadas:** VetCheck base (driver JDBC) + pruebas reales contra clústeres desechables de PostgreSQL 17 y PostgreSQL 18 con SCRAM-SHA-256 (11 aserciones en cada versión; clústeres detenidos).
- **Definición de comprobaciones:** [`checks/projects.json`](checks/projects.json) (ejecutable tras compilar con `.\build-local.ps1`).
- Configuración PostgreSQL 17/18 por variables de entorno: `VET_JDBC_URL` (o `VET_DATABASE`), `VET_USER`, `VET_PASSWORD`. Para pruebas de integración contra una BD temporal, define además `VET_TEST_DATABASE=1`.

<!-- improvements-20261119:end -->
