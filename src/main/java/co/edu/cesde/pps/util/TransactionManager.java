package co.edu.cesde.pps.util;

import jakarta.persistence.EntityManager;

/**
 * LEGACY: este helper queda solo como referencia historica.
 *
 * En Spring Boot el reemplazo oficial es @Transactional.
 */
public final class TransactionManager {

    private static final String LEGACY_MESSAGE = "TransactionManager is legacy and must not be used in the Spring Boot runtime";

    private TransactionManager() {
        throw new AssertionError("TransactionManager is a utility class and cannot be instantiated");
    }
    public static <T> T executeInTransaction(java.util.function.Function<EntityManager, T> operation) {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static void executeInTransaction(java.util.function.Consumer<EntityManager> operation) {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
    public static <T> T executeReadOnly(java.util.function.Function<EntityManager, T> operation) {
        throw new UnsupportedOperationException(LEGACY_MESSAGE);
    }
}
