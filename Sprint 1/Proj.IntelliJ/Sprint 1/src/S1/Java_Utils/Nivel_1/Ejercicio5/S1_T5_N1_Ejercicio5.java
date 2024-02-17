package S1.Java_Utils.Nivel_1.Ejercicio5;
import java.io.IOException;

/**
 * ********************** Serializacion / Deserialización ****************
 *
 * Java facilita el almacenamiento y transmisión del estado de un objeto
 * mediante un mecanismo conocido con el nombre de serialización.
 * La serialización de un objeto consiste en generar una secuencia de
 * bytes lista para su almacenamiento o transmisión.
 * Mediante la deserialización, el estado original del objeto se puede reconstruir.
 * Para que un objeto sea serializable, ha de implementar la interfaz
 * java.io.Serializable (que lo único que hace es marcar el objeto
 * como serializable, sin que tengamos que implementar ningún método).
 */
public class S1_T5_N1_Ejercicio5 {
    /**
     * NOTA:
     * En la clase FileDataInput manejo las excepciones dentro de la misma clase.
     *
     * Las excepciones de la clase FileDataOutput son manejadas en el momento en que se llama a los metodos de la misma
     * que pueden lanzar excepciones
     */
    public static void main(String[] args) {
        String serializeRoute = "EjercicioSerializar";
        //  "Clase del objeto que se va a Serializar".
        FileData toSer = new FileData("MyFile", "File", "2023-11-26");
        // Serializar
        System.out.println("Serializando...");
        serializeObject(toSer,serializeRoute);
        // Deserializar
        System.out.println("Deserializando... :");
        FileData getDeser = deserializeObject(serializeRoute);
        // Check deserialazacion
        /**
         * Cuando intentamos acceder a los atributos de un objeto null, nos arroja NullPointerException.
         * Por lo tanto, es NECESARIO checkear que el objeto "getDeser" no sea null antes de acceder a sus atributos.
         */
        if(getDeser != null)
        {
            System.out.println(getDeser.getName() + " " + getDeser.getType() + " " + getDeser.getLastModification());
        }
    }

    public static void serializeObject(FileData object, String serializeRoute) {
        // Clase que Serializa
        FileDataOutput os = new FileDataOutput();
        try
        {
            os.open(serializeRoute);
            os.write(object);
        }catch(IOException e)
        {
            System.out.println("Error al leer el archivo" + e.getMessage());
        }finally
        {
            try {
                //  Manejo de excepcion "NULLPOINTEREXCEPTION". Me aseguro que no sea nulo el objeto ObjectOutputStream
                //  al cerrarlo.
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo" + e.getMessage());
            }
        }
    }
    public static FileData deserializeObject(String serializeRoute)
    {
        // Clase que Deserializa (FileDataInput)
        FileDataInput input = new FileDataInput();
        FileData ret = (FileData) input.read(serializeRoute);
        return ret;
    }
}
