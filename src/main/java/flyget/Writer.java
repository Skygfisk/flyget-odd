package flyget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class to save and load class Objects from a file
 */
public class Writer {

    /**
     * Saves the given object as a file
     * 
     * @param file file path to save or override
     * @param obj  class to be saved
     * @throws IOException
     */
    public static void writeToFile(File file, Object obj) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
    }

    /**
     * Loads a Object from the given file
     * 
     * @param file file path to load from
     * @return a Object from the file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readFromFile(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return objectInputStream.readObject();
        }
    }
}
