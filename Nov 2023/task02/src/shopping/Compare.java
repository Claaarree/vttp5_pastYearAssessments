package shopping;

import java.util.Comparator;

public class Compare implements Comparator<Product>{

    @Override
    public int compare(Product p1, Product p2) {
        //System.out.println("in compare");
        if (p1.getRating() != p2.getRating()) {
            return p2.getRating() - p1.getRating();
        } else {
            return p2.getPrice() - p1.getPrice();
        }
    }

    
}
