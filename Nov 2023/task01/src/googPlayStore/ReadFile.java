package googPlayStore;

import java.io.*;
import java.util.*;

public class ReadFile {
    private int linesRead = 0;

    public int getLinesRead() {
        return linesRead;
    }

    public Map<Category , Map<String, Double>> readCSV(File csvFile){
        Map<Category , Map<String, Double>> categories = new HashMap<>();
        Map <String, Double> AppMap = new HashMap<>();;

        try {
            Reader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null){
                //Skipping first line of headers
                br.readLine();

                linesRead++;
                String[] lineSplit = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                String appName = lineSplit[0];
                String categoryName = lineSplit[1].toLowerCase();
                Category cat = new Category(categoryName);
                String rating = lineSplit[2];
                if (!categories.containsKey(cat)){
                    categories.put(cat, new HashMap<String, Double>());
                } else{
                    AppMap = categories.get(cat);
                }
                Double discardedLines = 0.0;
                double ratingNumber = 0.0;
                try {
                    ratingNumber = Double.parseDouble(rating);
                    AppMap.put(appName, ratingNumber);
                    //System.out.println("in try");
                } catch (Exception e) {
                    if (!AppMap.containsKey("discarded")){
                        AppMap.put("discarded", discardedLines);
                    } else {
                        //System.out.println("in catch");
                        discardedLines = AppMap.get("discarded");
                        discardedLines++;
                    }
                } 
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return categories;

    }
}
