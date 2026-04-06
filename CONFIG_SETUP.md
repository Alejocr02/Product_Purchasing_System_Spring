# Configuracion del ambiente - Spring Boot + .env

## Alcance de esta guia

Esta guia documenta unicamente la configuracion que sigue vigente en el proyecto:

- Spring Boot
- `application.yml`
- carga local de `.env`
- variables de entorno para DDL y logging
- uso de `APP_ENVIRONMENT` como apoyo de configuracion

### Ya no hacen parte del flujo activo

- `src/main/java/co/edu/cesde/pps/config/DatabaseConfig.java`
- `src/main/java/co/edu/cesde/pps/config/JpaConfig.java`
- `src/main/java/co/edu/cesde/pps/util/TransactionManager.java`
- `src/main/resources/META-INF/persistence.xml`

## Supuestos previos

La conectividad base ya fue validada previamente, por lo tanto esta guia no repite:

- instalacion del motor MySQL
- creacion manual de base de datos
- validacion del usuario de conexion
- pruebas del motor o del esquema

Esta documentacion parte de que el ambiente de base de datos ya existe y ya fue probado.

## Flujo vigente de configuracion

### 1) Crear el archivo local `.env`

```bash
cp .env.example .env
```

### 2) Ajustar unicamente los valores reales del ambiente

El archivo `.env.example` esta orientado a despliegue/configuracion controlada.
El archivo `.env` local es donde se asignan los valores reales del ambiente.

Variables relevantes:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`
- `DB_DDL_AUTO`
- `DB_SHOW_SQL`
- `DB_POOL_SIZE`
- `APP_ENVIRONMENT`
- `LOG_LEVEL`
- `LOG_SQL_LEVEL`
- `LOG_SQL_BIND_LEVEL`

## Como se carga `.env`

La aplicacion carga `.env` al iniciar mediante:

- la dependencia `java-dotenv`
- la clase `DotenvDevelopmentLoader`
- el arranque desde `PpsApplication`

`DotenvDevelopmentLoader` tambien traduce `SPRING_PROFILES_ACTIVE` a `spring.profiles.active`
para que Spring Boot reciba el perfil activo sin configuracion adicional.

### Orden de prioridad

Si una variable ya existe como:

- variable de entorno real del sistema, o
- system property

entonces no se sobrescribe con el valor del archivo `.env`.

Esto permite:

- usar `.env` en desarrollo local
- usar variables reales del servidor en otros ambientes

## Configuracion activa del proyecto

La configuracion activa esta en:

- `src/main/resources/application.yml`

Spring Boot resuelve desde alli:

- datasource
- JPA / Hibernate
- Hikari pool
- logging

### Fragmento vigente

```yaml
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:pps_db}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: ${DB_USER:user_pps}
    password: ${DB_PASSWORD:}
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      maximum-pool-size: ${DB_POOL_SIZE:10}
  jpa:
    hibernate:
      ddl-auto: ${DB_DDL_AUTO:update}
    open-in-view: false
    show-sql: ${DB_SHOW_SQL:true}

logging:
  level:
    co.edu.cesde.pps: ${LOG_LEVEL:DEBUG}
    org.hibernate.SQL: ${LOG_SQL_LEVEL:DEBUG}
    org.hibernate.orm.jdbc.bind: ${LOG_SQL_BIND_LEVEL:TRACE}
```

## Scripts SQL disponibles

Se mantienen como referencia operativa:

- `src/main/resources/sql/schema.sql`
- `src/main/resources/sql/data.sql`

Estos archivos pueden seguir usandose como apoyo del proyecto, pero esta guia ya no documenta la creacion inicial del motor/base/usuario.

## Variables recomendadas por ambiente

### Produccion

```properties
DB_DDL_AUTO=none
DB_SHOW_SQL=false
APP_ENVIRONMENT=production
LOG_LEVEL=INFO
LOG_SQL_LEVEL=WARN
LOG_SQL_BIND_LEVEL=OFF
```

### Desarrollo controlado

```properties
DB_DDL_AUTO=update
DB_SHOW_SQL=true
APP_ENVIRONMENT=development
LOG_LEVEL=DEBUG
LOG_SQL_LEVEL=DEBUG
LOG_SQL_BIND_LEVEL=TRACE
```

## Troubleshooting vigente

### `.env` no se refleja

Validar:

- que `.env` exista en la raiz del proyecto
- que `PpsApplication` invoque `DotenvDevelopmentLoader.load()`
- que la variable no exista ya en el entorno con otro valor

### La aplicacion no arranca

Validar:

```bash
mvn clean compile
```

Y revisar:

- valores obligatorios del datasource
- disponibilidad del ambiente de base de datos
- configuracion de `DB_DDL_AUTO`

### El logging no coincide con lo esperado

Revisar:

- `LOG_LEVEL`
- `LOG_SQL_LEVEL`
- `LOG_SQL_BIND_LEVEL`
- `APP_ENVIRONMENT`

## Notas importantes

- `application.yml` es la fuente principal de configuracion.
- `.env` se usa como apoyo practico para desarrollo local.
- `APP_ENVIRONMENT` permanece como variable auxiliar de configuracion.
- `DatabaseConfig`, `JpaConfig` y `TransactionManager` quedan solo como historico.
- `persistence.xml` no debe volver al classpath activo.

## Checklist

- [ ] `.env` creado desde `.env.example`
- [ ] valores reales del ambiente configurados
- [ ] `application.yml` usado como fuente principal
- [ ] `.env` cargado por `DotenvDevelopmentLoader`
- [ ] `APP_ENVIRONMENT` definido segun el ambiente
- [ ] `DB_DDL_AUTO` definido correctamente
- [ ] logging ajustado con `LOG_LEVEL`, `LOG_SQL_LEVEL` y `LOG_SQL_BIND_LEVEL`
- [ ] `DatabaseConfig`, `JpaConfig` y `TransactionManager` sin uso activo
- [ ] `persistence.xml` fuera del flujo activo
