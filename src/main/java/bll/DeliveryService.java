package bll;

import com.sun.org.apache.xml.internal.serializer.Version;
import interfaces.Observer;
import dataLayer.FileWriter;
import interfaces.Administrator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService {
    private static ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    private static ArrayList<User> users = new ArrayList<>();
    private static HashMap<Order, ArrayList<MenuItem>> hmap = new HashMap<>();
    private final BiFunction<List<MenuItem>, Predicate<MenuItem>, List<MenuItem>> PRODUCT_FILTER =
            (menuItem, fields) -> menuItem.stream().filter(fields).collect(Collectors.toList()); //functie pentru expresii lambda
    private CompositeProduct compositeProduct;
    private static List<Observer> observers;


    public void importProducts(MenuItem m) {
        assert m != null;
        menu.add(m);
        creatTableModel();
        assert menu.contains(m);
        assert isWellFormed();
    }
    public void addDataToTable(DefaultTableModel model, MenuItem menuItem) {
        String t = menuItem.getTitle();
        float r = menuItem.getRating();
        int f = menuItem.getFats();
        int c = menuItem.getCalories();
        int p = menuItem.getProteins();
        int s = menuItem.getSodium();
        int pr = (int) menuItem.getPrice();
        Object[] data = {t, r, f, c, p, s, pr};
        model.addRow(data);
    }
   // @Override
    public void deleteProducts(String str) {
        assert !str.equals("");
        for (MenuItem menuItem : menu) {
            if (menuItem.getTitle().equals(str)) {
                menu.remove(menuItem);
                break;
            }
        }
        creatTableModel();
        assert isWellFormed();
    }


    public void editProducts() {
        int index = interfaces.Administrator.getTable().getSelectedRow();
        BaseProduct product = getProduct(interfaces.Administrator.getTable());
        menu.get(index).setTitle(product.getTitle());
        menu.get(index).setPrice((int) product.getPrice());
        menu.get(index).setCalories(product.getCalories());
        menu.get(index).setFats(product.getFats());
        menu.get(index).setRating(product.getRating());
        menu.get(index).setSodium(product.getSodium());
        menu.get(index).setProteins(product.getSodium());
        creatTableModel();
    }

    public void doColumnsResize(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(195);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);
        table.getColumnModel().getColumn(4).setPreferredWidth(15);
        table.getColumnModel().getColumn(5).setPreferredWidth(25);
        table.getColumnModel().getColumn(6).setPreferredWidth(5);
    }
    private void creatTableModel() {
        String[] col = {"Title", "Rating", "Fat", "Calories", "Protein", "Sodium", "Price"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        menu.forEach(menuItem -> addDataToTable(model, menuItem));
        interfaces.Administrator.getTable().setModel(model);
        doColumnsResize(interfaces.Administrator.getTable());
    }

    public void generateReport1(int start, int end) {
        assert start > 0 && end > 0;
        StringBuilder s = new StringBuilder();
        s.append("Report: time interval of the orders \n");
        hmap.entrySet().stream().filter(entry-> entry.getKey().getDate().getHours() >=start &&entry.getKey().getDate().getHours()<=end).forEach(entry -> {
            s.append(entry.getValue().toString() + " ");
            s.append(entry.getKey().getDate().toString());
            s.append("\n");
        });
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();

    }
    public void generateReport2(int nr) {
        assert nr > 0;
        StringBuilder s = new StringBuilder();
        s.append("Report: the products ordered more than a specified number of times\n");
        menu.stream().filter(menuItem -> menuItem.getNr()>nr).forEach(menuItem -> {
            s.append(menuItem.getTitle() + " " + menuItem.getNr() + "\n");
        });
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();
    }
    public void generateReport3(int nr, int val) {
        assert nr > 0 && val > 0;
        StringBuilder s = new StringBuilder();
        List<String> clients= new ArrayList<>();
        s.append("Report: the clients that have ordered more than a specified number of times and the value \n").append("of the order was higher than a specified amount.\n");
        hmap.entrySet().stream().filter(entry -> entry.getKey().getSum() > val).forEach(entry -> users.stream().filter(c -> c.getContor() > nr).forEach(c -> {
            clients.add(String.valueOf(c.getID()));
        }));
        List<String>noDup=  clients.stream().distinct().collect(Collectors.toList());
        for(String id: noDup){
            s.append("Client id: "+ id);
        }
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();
    }

    public static ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public static void setMenu(ArrayList<MenuItem> menu) {
        DeliveryService.menu = menu;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        DeliveryService.users = users;
    }

    public static HashMap<Order, ArrayList<MenuItem>> getHmap() {
        return hmap;
    }

    public static void setHmap(HashMap<Order, ArrayList<MenuItem>> hmap) {
        DeliveryService.hmap = hmap;
    }

    public static List<Observer> getObservers() {
        return observers;
    }

    public BiFunction<List<MenuItem>, Predicate<MenuItem>, List<MenuItem>> getPRODUCT_FILTER() {
        return PRODUCT_FILTER;
    }

    public CompositeProduct getCompositeProduct() {
        return compositeProduct;
    }

    public void setCompositeProduct(CompositeProduct compositeProduct) {
        this.compositeProduct = compositeProduct;
    }

    public static void setObservers(List<Observer> observers) {
        DeliveryService.observers = observers;
    }

    public boolean isWellFormed() {
        return menu != null;
    }

    public BaseProduct getProduct(JTable table) {
        int index = table.getSelectedRow();
        String title = table.getModel().getValueAt(index, 0).toString();
        float rating = Float.parseFloat(table.getModel().getValueAt(index, 1).toString());
        int fats = Integer.parseInt(table.getModel().getValueAt(index, 2).toString());
        int calories = Integer.parseInt(table.getModel().getValueAt(index, 3).toString());
        int protein = Integer.parseInt(table.getModel().getValueAt(index, 4).toString());
        int sodium = Integer.parseInt(table.getModel().getValueAt(index, 5).toString());
        int price = Integer.parseInt(table.getModel().getValueAt(index, 6).toString());
        return new BaseProduct(title, rating, calories, protein, fats, sodium, price);
    }
}
