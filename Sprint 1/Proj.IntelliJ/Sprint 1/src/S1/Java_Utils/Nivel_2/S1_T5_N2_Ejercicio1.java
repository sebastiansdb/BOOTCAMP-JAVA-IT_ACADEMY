package S1.Java_Utils.Nivel_2;

import java.util.Properties;

public class S1_T5_N2_Ejercicio1 {
    public static void runApp() {
        Properties properties = Config.initProperties();
        String directoryToRead = properties.getProperty("directory_to_read");
        String outputFileName = properties.getProperty("output_file_name");
        TxtWriter.writeFileContentAtTxt(outputFileName, directoryToRead);
    }
}
