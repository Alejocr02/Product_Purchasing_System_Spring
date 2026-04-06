package co.edu.cesde.pps.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Carga variables desde .env para desarrollo local sin sobrescribir
 * variables del sistema o propiedades JVM ya definidas.
 */
public final class DotenvDevelopmentLoader {

    private static final Logger log = LoggerFactory.getLogger(DotenvDevelopmentLoader.class);

    private DotenvDevelopmentLoader() {
        throw new AssertionError("DotenvDevelopmentLoader is a utility class and cannot be instantiated");
    }

    public static void load() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        int loadedCount = 0;
        for (DotenvEntry entry : dotenv.entries()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Mantener prioridad: variable de entorno real o propiedad JVM existente.
            if (System.getenv(key) != null || System.getProperty(key) != null) {
                continue;
            }

            System.setProperty(key, value);

            if ("SPRING_PROFILES_ACTIVE".equalsIgnoreCase(key)
                    && System.getProperty("spring.profiles.active") == null) {
                System.setProperty("spring.profiles.active", value);
            }

            loadedCount++;
        }

        if (loadedCount > 0) {
            log.info("Loaded {} properties from local .env", loadedCount);
        } else {
            log.debug("No .env properties were loaded (already defined or file missing)");
        }
    }
}

