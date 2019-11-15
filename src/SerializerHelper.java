import java.io.*;

public class SerializerHelper {
    public static void serializeObject(Object object, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            // System.out.println("Serialised object stored as " + filename);
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Object deSerializeObject(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            bufferedInputStream.close();
            fileInputStream.close();
            // System.out.println("Object has been read in!");
            return object;
        }
        catch (FileNotFoundException f) {
            System.out.println("FileNotFoundException caught!");
//            f.printStackTrace();
            return null;
        }
        catch (IOException i)
        {
            System.out.println("IOException caught!");
//            i.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found!");
//            c.printStackTrace();
            return null;
        }
    }
}
