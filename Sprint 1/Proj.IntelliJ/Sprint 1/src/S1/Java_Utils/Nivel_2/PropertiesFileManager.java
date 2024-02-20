package S1.Java_Utils.Nivel_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileManager {
    Properties config;

    public PropertiesFileManager(String configProperties) {
        this.config = new Properties();
        try (FileInputStream configInput = new FileInputStream(configProperties)) {
            config.load(configInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileToRead(String path) {

    }
}
