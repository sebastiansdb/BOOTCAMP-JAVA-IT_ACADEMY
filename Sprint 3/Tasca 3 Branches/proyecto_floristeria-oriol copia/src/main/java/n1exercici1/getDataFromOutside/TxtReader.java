package n1exercici1.getDataFromOutside;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtReader {
    public static List<String[]> getTxtLines(String fileToRead) throws RuntimeException {
        String wordsChain;
        List<String[]> txtLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            while ((wordsChain = br.readLine()) != null) {
                String[] lines = wordsChain.split(";");
                txtLines.add(lines);
            }
        } catch (IOException e) {
            System.out.println("La base de datos 'TXT' no existe o se proporcionó una ruta errónea");
        }
        return txtLines;
    }

}


