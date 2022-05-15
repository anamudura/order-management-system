package bll;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;
    private float rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private int price;
    private int nr;
    public MenuItem()
    {

    }
    public MenuItem(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        this.title = title;
        this.price = price;
        this.calories = calories;
        this.fats = fats;
        this.proteins = proteins;
        this.rating = rating;
        this.sodium = sodium;
        setNr(0);
    }
    @Override
    public String toString() {
        return getTitle() + " " + getPrice() + " " + getCalories() + " " + getFats() + " " + getProteins() + " " + getSodium() + " " + getRating() + "\n";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
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

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public abstract float computePrice();
}
