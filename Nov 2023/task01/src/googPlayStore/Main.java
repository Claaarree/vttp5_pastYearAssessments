package googPlayStore;

import java.io.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String csvFilename = args[0];
        File csvFile;
        if (args.length < 1){
            System.out.println("Usage: java -cp classes googPlayStore/Main <CSV filename>");
            System.out.println("Please enter a file name before running the program!");
            return;
        } else {
            csvFile = new File(csvFilename);
        }
        ReadFile fileRead = new ReadFile();
        Map <Category, Map <String, Double>> categoriesMap = fileRead.readCSV(csvFile);
        
        for (Category cat : categoriesMap.keySet()){
            Map <String, Double> appMap = cat.getAppMap(categoriesMap);
            cat.toString(appMap);            
        }
        System.out.printf("Total lines in file: %d\n", fileRead.getLinesRead());

        


    }
}
