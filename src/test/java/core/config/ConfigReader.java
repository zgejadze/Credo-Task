package core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config.properties"
            );
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("core.config.properties not found", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}