package googPlayStore;

import java.io.*;
import java.util.*;

public class ReadFile {
    private int linesRead = 0;

    public int getLinesRead() {
        return linesRead;
    }

    public List<Category> readCSV(File csvFile) {
        List<Category> categories = new ArrayList<>();

        try {
            Reader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            // Skipping first line of headers
            br.readLine();

            while ((line = br.readLine()) != null) {
            
                linesRead++;
                String[] lineSplit = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                String appName = lineSplit[0];
                String categoryName = lineSplit[1].toLowerCase();
                String rating = lineSplit[2];

                if (categories.size() == 0){
                    Category cat = new Category(categoryName);
                    categories.add(cat);
                }
                
                int helper = 0;
                for(int i = 0 ; i < categories.size(); i++){
                    if (!categoryName.equals((categories.get(i)).getCatName())){
                        helper++;
                    }
                }
                if (helper == categories.size()) {
                    categories.add(new Category(categoryName));
                }

                // System.out.println(categories);
                
                for(int i = 0 ; i < categories.size(); i++){
                    double ratingNumber = Double.parseDouble(rating);
                    if ((categories.get(i)).getCatName().equals(categoryName)){
                        categories.get(i).addToMap(appName, ratingNumber);
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
