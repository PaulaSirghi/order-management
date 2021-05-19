package businessLayer;

public class BaseProduct extends MenuItem {
    public BaseProduct(String title, String rating, String calories, String protein, String fats, String sodium, String price) {
            super(title, rating, calories, protein, fats, sodium, price);
    }
    public BaseProduct(){}
}
