package googPlayStore;

import java.util.Map;

public class Category {
    private String catName;
    private int discarded;
    
    


    public Category(String catName) {
        this.catName = catName;
    }

    public Map<String, Double> getAppMap(Map <Category, Map <String, Double>> categoriesMap){
        Map <String, Double> appMap = categoriesMap.get((Object)catName);
        return appMap;
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
        double worstRating = 0.0;
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
    
    public int getDiscarded(Map <String, Double> appMap) {
        discarded = appMap.get("discarded").intValue();
        return discarded;
    }


    public void toString(Map <String, Double> appMap) {
        System.out.printf("Category: %s\n", catName.toUpperCase());
        System.out.printf("    Highest: %s, %f\n", getBestApp(appMap), appMap.get(getBestApp(appMap)));
        System.out.printf("    Lowest: %s, %f\n", getWorstApp(appMap), appMap.get(getWorstApp(appMap)));
        System.out.printf("    Average: %f\n", getAvgRating(appMap));
        System.out.printf("    Discarded: %d\n\n", getDiscarded(appMap));
    }
    
    
    
    
    
}
