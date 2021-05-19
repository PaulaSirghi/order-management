package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fats;
    private int sodium;
    private int price;
    public MenuItem(){

    }
    public MenuItem(String title, String rating, String calories, String protein, String fats, String sodium, String price) {
        try{
            this.setTitle(title);
            this.setRating(Double.parseDouble(rating));
            this.setCalories(Integer.parseInt(calories));
            this.setProtein(Integer.parseInt(protein));
            this.setFats(Integer.parseInt(fats));
            this.setSodium(Integer.parseInt(sodium));
            this.setPrice(Integer.parseInt(price));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
