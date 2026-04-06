package co.edu.cesde.pps.config;

/**
 * LEGACY: esta clase queda solo como referencia historica.
 *
 * Spring Boot + application.yml reemplaza toda esta configuracion.
 */
public final class DatabaseConfig {
    private static final String LEGACY_MESSAGE = "DatabaseConfig is legacy and must not be used in the Spring Boot runtime";
    private static final String DEFAULT_DB_HOST = "localhost";
    private static final String DEFAULT_DB_PORT = "3306";
    private static final String DEFAULT_DB_NAME = "pps_db";
    private static final String DEFAULT_DB_USER = "user_pps";
    private static final String DEFAULT_DB_PASSWORD = "";
    private static final String DEFAULT_DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static final String DEFAULT_DDL_AUTO = "update";
    private static final boolean DEFAULT_SHOW_SQL = true;
    private static final boolean DEFAULT_FORMAT_SQL = true;
    private static final boolean DEFAULT_USE_SQL_COMMENTS = true;
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final int DEFAULT_MIN_POOL_SIZE = 5;
    private static final int DEFAULT_MAX_POOL_SIZE = 20;
    private static final int DEFAULT_IDLE_TIMEOUT = 300000;
    private DatabaseConfig() {
        throw new AssertionError("DatabaseConfig is a utility class and cannot be instantiated");
    }
    public static String getDbHost() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static String getDbPort() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static String getDbName() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static String getDbUser() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static String getDbPassword() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static int getPoolSize() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static String getJdbcUrl() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static String getDriverClassName() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static String getHibernateDialect() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static String getHibernateDdlAuto() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static boolean isShowSql() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static boolean isFormatSql() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static boolean isUseSqlComments() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static int getMinPoolSize() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static int getMaxPoolSize() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
    public static int getIdleTimeout() { throw new UnsupportedOperationException(LEGACY_MESSAGE); }
}
