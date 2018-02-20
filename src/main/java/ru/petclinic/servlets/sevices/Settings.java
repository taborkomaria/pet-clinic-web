package ru.petclinic.servlets.sevices;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private static Settings INSTANCE = new Settings();

    private final Properties properties = new Properties();

    private Settings() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream resourceStream = loader.getResourceAsStream("petclinic.properties")) {
            properties.load(resourceStream);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return INSTANCE;
    }

    public String value(String key) {
        return this.properties.getProperty(key);
    }
}
