package bll;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{
    private ArrayList<MenuItem> list = new ArrayList<MenuItem>();


    public CompositeProduct(String title, float rating, int calories, int proteins, int fats, int sodium) {
        super(title, rating, calories, proteins, fats, sodium, 0);
    }

    public CompositeProduct() {

    }

    public float computePrice()
    {
        int p = 0;
        for(MenuItem m: list)
            p = p + m.getPrice();
        super.setPrice(p);
        return p;
    }


    public ArrayList<MenuItem> getList() {
        return list;
    }

    public void setList(ArrayList<MenuItem> list) {
        this.list = list;
    }
}
