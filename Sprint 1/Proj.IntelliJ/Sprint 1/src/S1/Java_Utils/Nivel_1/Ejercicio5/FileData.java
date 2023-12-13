package S1.Java_Utils.Nivel_1.Ejercicio5;

import java.io.Serializable;

public class FileData implements Serializable {
    /**
     * Para que un objeto sea serializable, todas sus variables de instancia han de ser serializables.
     * Todos los tipos primitivos en Java son serializables por defecto (igual que los arrays y otros muchos tipos estándar)
     *
     * //    En este caso, lo que elijo para serializar es la información descriptiva de un archivo o carpeta. Para esto,
     *                          creo la clase FILEDATA, que almacena dichas caracteristicas.                          //
     */
    private String name;
    private String type;    // Directorio o Archivo
    private String lastModification;

    public FileData(String name, String type,String lastModification) {
        this.name = name;
        this.type = type;
        this.lastModification = lastModification;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
    public String getLastModification(){
        return this.lastModification;
    }

}