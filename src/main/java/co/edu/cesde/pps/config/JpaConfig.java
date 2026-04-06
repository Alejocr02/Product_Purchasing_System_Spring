package co.edu.cesde.pps.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * LEGACY: esta clase queda solo como referencia historica.
 *
 * Spring Boot gestiona JPA de forma automatica y esta clase no debe usarse.
 */
public final class JpaConfig {

    private static final String LEGACY_MESSAGE = "JpaConfig is legacy and must not be used in the Spring Boot runtime";

    private JpaConfig() {
        throw new AssertionError("JpaConfig is a utility class and cannot be instantiated");
    }
    public static EntityManagerFactory getEntityManagerFactory() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static EntityManager createEntityManager() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static void close() {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
}
