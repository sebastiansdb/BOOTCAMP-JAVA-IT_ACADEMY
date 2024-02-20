package n3exercici1.sales;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketPrinter {

    private static final String directory = "src/main/resources/";

    public static void printTicketToTXT(String ticket, int idSale){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(directory+"Ticket"+idSale+".txt").getAbsoluteFile()))){
            bw.write(ticket);
        } catch (IOException e){
            System.out.println("There was an error printing the ticket at the specified path.");
        }
    }

}
