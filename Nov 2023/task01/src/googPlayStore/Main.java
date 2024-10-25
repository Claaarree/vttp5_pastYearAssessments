package googPlayStore;

import java.io.*;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String csvFilename = args[0];
        //"Nov 2023/task01/googleplaystore.csv";
        File csvFile = new File(csvFilename);
        if (args.length < 1){
            System.out.println("Usage: java -cp classes googPlayStore/Main <CSV filename>");
            System.out.println("Please enter a file name before running the program!");
            return;
        } else {
            csvFile = new File(csvFilename);
        }
        ReadFile fileRead = new ReadFile();
        List<Category> categories = fileRead.readCSV(csvFile);
        
        for (Category cat : categories){
            cat.printString();            
        }
        System.out.printf("Total lines in file: %d\n", fileRead.getLinesRead());

        


    }
}
