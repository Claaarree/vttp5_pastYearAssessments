package shopping;

import java.io.*;
import java.util.*;

public class Product {
    private int prodID;
    private String title;
    private double price;
    private double rating;

    public Product(int prodID, String title, double price, double rating) {
        this.prodID = prodID;
        this.title = title;
        this.price = price;
        this.rating = rating;
    }



    public int getProdID() {
        return prodID;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public static ArrayList<Product> getProductList() throws IOException {
        File productListFile = new File("C:\\Users\\Clare Lau\\vttp5_sdf_pastAssessments\\Nov 2023\\task02\\prod_list.csv");
        FileReader fr = new FileReader(productListFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> linesRead = new ArrayList<>();
        while ((line = br.readLine()) != null) {               
            linesRead.add(line);
        }
        br.close();
        fr.close();
        
        String prodIDLine = linesRead.get(0);
        String prodTitleLine = linesRead.get(1);
        String prodPriceLine = linesRead.get(2);
        String prodRatingLine = linesRead.get(3);

        String[] prodID = prodIDLine.split(",");
        String [] prodTitle = prodTitleLine.split(",");
        String [] prodPrice = prodPriceLine.split(",");
        String [] prodRating = prodRatingLine.split(",");

        ArrayList<Product> productList = new ArrayList<>();
        for (int i = 1; i < prodID.length; i++) {
            Product product = new Product(Integer.parseInt(prodID[i]), prodTitle[i], Integer.parseInt(prodPrice[i]), Integer.parseInt(prodRating[i]));
            productList.add(product);
            //System.out.println(product.toString());
            
        }

        return productList;

       
    }
    
    public static int getItemcount() throws IOException {
        ArrayList<Product> productList = getProductList();
        Random random = new Random();
        int itemCount = random.nextInt(productList.size()) + 1;

        return itemCount;
    }

    public static ArrayList<Product> getGenProductList(int itemCount) throws IOException {
        ArrayList<Product> productList = getProductList();
        ArrayList<Product> genProductList = new ArrayList<>();
        for (int i = 0 ; i < itemCount; i++) {
            Random random = new Random();
            Product randomProduct = productList.get(random.nextInt(productList.size()));
            genProductList.add(randomProduct);
        }
        return genProductList;
    }

    

    
}
