package S1.Java_Utils.Nivel_1.Ejercicio5;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileDataOutput {

    private FileOutputStream file;
    private ObjectOutputStream output;

    public void open(String fileToOpen) throws IOException
    {
       this.file = new FileOutputStream(fileToOpen);
       this.output = new ObjectOutputStream(file);
    }
    /**
    * Writes the given data object to the output stream.
    *
    * @param dataToWrite the object to write
    * @throws IOException if an I/O error occurs
    */
    public void write(Object dataToWrite) throws IOException {
        //  Manejo de excepcion "NULLPOINTEREXCEPTION". Me aseguro que no sea nulo el objeto a escribir..
        if(output != null) {
            output.writeObject(dataToWrite);
        }
    }
    /**
     * Closes the output stream.
     *
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException
    {
        if(output != null) {
            output.close();
        }
    }

}
