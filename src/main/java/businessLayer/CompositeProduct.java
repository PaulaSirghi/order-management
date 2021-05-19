package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    ArrayList<MenuItem> l=new ArrayList<>();
    public CompositeProduct(String title, String rating, String calories, String protein, String fats, String sodium, String price,ArrayList<MenuItem>a) {
        super(title, rating, calories, protein, fats, sodium, price);
        this.l=a;
    }
}
