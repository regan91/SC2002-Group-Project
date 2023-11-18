package HelperPackages.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BackupUtility {
    public static ArrayList<String> reader(String fileName){
        ArrayList<String> recordList = new ArrayList<>();
        try (Scanner fileReader = new Scanner(new File(fileName))){
            while (fileReader.hasNextLine()){
                recordList.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File was not found!");
            throw new RuntimeException(e);
        }
        return recordList;
    }
}
