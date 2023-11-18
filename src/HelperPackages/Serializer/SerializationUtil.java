package HelperPackages.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtil {

    public static ArrayList<String> reader(String fileName) throws IOException,ClassNotFoundException {

        ArrayList<String> extractedRecords = new ArrayList<>();

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayList<String> readerPoint = (ArrayList<String>) ois.readObject();
        ois.close();

//        extractedRecords.add(readerPoint);


        return readerPoint;
    }

    public static void writer(String fileName, List<String> data) throws IOException,ClassNotFoundException {

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.flush();
        oos.close();

    }
}