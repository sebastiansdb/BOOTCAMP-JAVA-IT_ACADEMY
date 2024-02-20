package S1.Java_Utils.Nivel_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    static Properties properties;

   static {
        properties = new Properties();
    }
    public static Properties initProperties() {
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        }
        return properties;
    }
}
