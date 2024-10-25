package googPlayStore;

import java.util.Map;
import java.util.HashMap;

public class Category {
    private String catName;
    private Map<String, Double> appMap;
    private double discarded = 0;
    
    


    public Category(String catName) {
        this.catName = catName;
        this.appMap = new HashMap<>();
    }


    public String getCatName() {
        return catName;
    }

    public void addToMap(String appName, Double rating){
        if (Double.isNaN(rating)){
            if (!appMap.containsKey("discarded")) {
                appMap.put("discarded", discarded);
            }
            discarded = appMap.get("discarded");
            discarded++;
            appMap.put("discarded", discarded);
        } else appMap.put(appName, rating);
    }

    public double getAvgRating(Map <String, Double> appMap) {
        double ratingSum = 0.0;
        for (String app : appMap.keySet()){
            if (app.equals("discarded")){
                continue;
            } else {
                ratingSum += appMap.get(app);
            }
        }
        double avgRating = ratingSum / (appMap.keySet().size() - 1);
        return avgRating;
    }
    
    
    public String getBestApp(Map <String, Double> appMap) {
        double bestRating = 0.0;
        String bestAppName = "";
        for (String app : appMap.keySet()){
            if (app.equals("discarded")){
                continue;
            } else{
                double rating = appMap.get(app);
                if (rating > bestRating){
                    bestRating = rating;
                    bestAppName = app;
                }
            }
        }
        return bestAppName;
    }
    
    public String getWorstApp(Map <String, Double> appMap) {
        double worstRating = 5.0;
        String worstAppName = "";
        for (String app : appMap.keySet()){
            if (app.equals("discarded")){
                continue;
            } else{
                double rating = appMap.get(app);
                if (rating < worstRating){
                    worstRating = rating;
                    worstAppName = app;
                }
            }
        }
        return worstAppName;
    }

    
    public void printString() {
        System.out.printf("Category: %s\n", catName.toUpperCase());
        System.out.printf("    Highest: %s, %f\n", getBestApp(appMap), appMap.get(getBestApp(appMap)));
        System.out.printf("    Lowest: %s, %f\n", getWorstApp(appMap), appMap.get(getWorstApp(appMap)));
        System.out.printf("    Average: %f\n", getAvgRating(appMap));
        System.out.printf("    Count: %d\n",appMap.size());
        System.out.printf("    Discarded: %f\n\n", appMap.get("discarded"));
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (o instanceof Category otherCat) {
            return otherCat.catName.equals(this.catName);
        }
        return false;
    }
    
    
    
    
}
