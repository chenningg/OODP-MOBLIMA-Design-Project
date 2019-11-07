import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializerHelper {
    private Object objectToSerialize;
    private String serializeFileName;

    // Methods
    public SerializerHelper(Object objectToSerialize, String serializeFileName) {
        this.objectToSerialize = objectToSerialize;
        this.serializeFileName = serializeFileName;
    }

    public void SerializeObject() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.serializeFileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.objectToSerialize);
            out.close();
            fileOut.close();
            System.out.println("Serialised object stored as " + this.serializeFileName);
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
}
