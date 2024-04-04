package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private static final String DEFAULT = "default.properties";

    private static final Properties props = new Properties();


    static {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        try (InputStream stream = loader.getResourceAsStream(DEFAULT)) {
            props.load(stream);
        } catch (IOException e) {
            System.err.println("No such file: " + DEFAULT);
            throw new RuntimeException(e);
        }
    }


    public static String getBrowser() {
        return props.getProperty("browser");
    }
}