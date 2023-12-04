package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsReader {
        private static PropsReader instance;
        private final Properties properties;

        private PropsReader() {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static synchronized PropsReader getInstance() {
            if (instance == null) {
                instance = new PropsReader();
            }
            return instance;
        }

        public String getProperty(String key) {
            return properties.getProperty(key);
        }
}
