package dataLayer;

import java.io.*;

public class FileWriter {

    public FileWriter() {

    }

    public static void write(String filename, String text) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            byte[] strToBytes = text.getBytes();
            fileOut.write(strToBytes);
            fileOut.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
