package bll;

import interfaces.Observer;
import dataLayer.FileWriter;
import interfaces.Client;
import interfaces.Administrator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, java.io.Serializable{
    private static ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    private static ArrayList<User> users = new ArrayList<>();
    private static HashMap<Order, ArrayList<MenuItem>> hmap = new HashMap<>();
    private final BiFunction<List<MenuItem>, Predicate<MenuItem>, List<MenuItem>> PRODUCT_FILTER =
            (menuItem, fields) -> menuItem.stream().filter(fields).collect(Collectors.toList()); //functie pentru expresii lambda
    private CompositeProduct compositeProduct;
    private static List<Observer> observers;

    public DeliveryService()
    {
        observers = new ArrayList<Observer>();
        bll.User u1 = new User("ana","2001",Rol.ADMINISTRATOR);
        bll.User u2 = new User("ana","28", Rol.EMPLOYEE);
        bll.User u3 = new User("ana","hei",Rol.CLIENT);

        users.add(u1);
        users.add(u2);
        users.add(u3);
    }
    /**
     * Adauga un element nou la meniu
     *
     * @param m produsul
     * @pre m != null;
     * @post se insereeaza produsul in tabela de meniuri
     * @invariant menu!=null
     */


    public void importProducts(MenuItem m) {
        assert m != null;
        menu.add(m);
        creatTableModel();
        assert menu.contains(m);
        assert isWellFormed();
    }
    public List<MenuItem> findProduct(ArrayList<MenuItem> list, String title, String rating, String calories, String proteins, String fats, String sodium, String price) {
        Predicate<MenuItem> byTitle = menuItem -> menuItem.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT));
        Predicate<MenuItem> byRating = menuItem -> String.valueOf(menuItem.getRating()).contains(String.valueOf(rating));
        Predicate<MenuItem> byCalories = menuItem -> String.valueOf(menuItem.getCalories()).contains(String.valueOf(calories));
        Predicate<MenuItem> byProtein = menuItem -> String.valueOf(menuItem.getProteins()).contains(String.valueOf(proteins));
        Predicate<MenuItem> byFat = menuItem -> String.valueOf(menuItem.getFats()).contains(String.valueOf(fats));
        Predicate<MenuItem> bySodium = menuItem -> String.valueOf(menuItem.getSodium()).contains(String.valueOf(sodium));
        Predicate<MenuItem> byPrice = menuItem -> String.valueOf(menuItem.getPrice()).contains(String.valueOf(price));
        return PRODUCT_FILTER.apply(list, byRating.and(byTitle).and(byCalories).and(byProtein).and(byFat).and(bySodium).and(byPrice));
    }

    /**
     * Cauta un produs in meniu
     *
     * @param title    numele produsului
     * @param rating   rating comansa
     * @param calories calorii
     * @param proteins proteine
     * @param fats     grasimi
     * @param sodium   sodiu
     * @param price    pretulq
     * @pre title!=" and rating!="" and calories!="" and proteins!="" and fats!="" and sodium!="" and price!="";
     * @post none
     * @invariant menu!=null
     */

    public void searchProduct(String title, String rating, String calories, String proteins, String fats, String sodium, String price) {
        assert !title.equals("") && !rating.equals("") && !calories.equals("") && !proteins.equals("") && !fats.equals("") && !sodium.equals("") && !price.equals("");
        String[] col = {"Title", "Rating", "Fat", "Calories", "Protein", "Sodium", "Price"};
        DefaultTableModel filtered = new DefaultTableModel(col, 0);
        List<MenuItem> menuItems = findProduct(DeliveryService.getMenu(), title, rating, calories, proteins, fats, sodium, price);
        menuItems.stream().forEach((menuItem -> addDataToTable(filtered, menuItem)
        ));
        interfaces.Client.setModel(filtered);
        interfaces.Client.getTable().setModel(interfaces.Client.getModel());
        doColumnsResize(Client.getTable());
        assert isWellFormed();
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

    /**
     * Creaza un produs compus
     *
     * @param title numele produsului compus
     * @pre title != "";
     * @post produsul compus != null
     * @invariant menu!=null
     */

    public void completeCompositeProduct(String title) {
        assert !title.equals("");
        float rating = 0;
        int calories = 0, fats = 0 , protein = 0, sodium = 0;
        title = title + ": ";
        for (MenuItem menuItem : compositeProduct.getList()) {
            title = title + menuItem.getTitle();
            title = title + ", ";
            rating = rating+ menuItem.getRating();
            calories = calories+ menuItem.getCalories();
            fats = fats + menuItem.getFats();
            protein = protein + menuItem.getProteins();
            sodium = sodium+ menuItem.getSodium();
        }
        rating = rating / compositeProduct.getList().size();
        CompositeProduct c = new CompositeProduct(title, rating, calories, protein, fats, sodium);
        c.setList(compositeProduct.getList());
        c.computePrice();
        menu.add(c);
        assert c != null;
        assert isWellFormed();

    }
    /**
     * Sterge un element
     *
     * @param s produsul care trebuie sters
     * @pre str != "";
     * @post none
     * @invariant menu!=null
     */

    public void deleteProducts(String s) {
        assert !s.equals("");
        for (MenuItem menuItem : menu) {
            if (menuItem.getTitle().equals(s)) {
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

    /**
     * Genereaza un raport cu comenzile cuprinze intre doua ore date ca si parametru
     *
     * @param start ora de inceput
     * @param end   ora de sfarsit
     * @pre start > 0 and end > 0;
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    public void generateReport1(int start, int end) {
        assert start > 0 && end > 0;
        StringBuilder s = new StringBuilder();
        s.append("Report: time interval of the orders \n");
        hmap.entrySet().stream().filter(entry -> entry.getKey().getDate().getHours() >= start && entry.getKey().getDate().getHours() <= end).forEach(entry -> {
            s.append(entry.getValue().toString() + " ");
            s.append(entry.getKey().getDate().toString());
            s.append("\n");
        });
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();

    }
    /**
     * Genereaza un raport cu produsele comandate de mai multe ori decat numarul dat ca parametru
     *
     * @param nr numarul minim de comenzi al unui produs
     * @pre nr > 0;
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    public void generateReport2(int nr) {
        assert nr > 0;
        StringBuilder s = new StringBuilder();
        s.append("Report: the products ordered more than a specified number of times\n");
        menu.stream().filter(menuItem -> menuItem.getNr() > nr).forEach(menuItem -> {
            s.append(menuItem.getTitle() + " " + menuItem.getNr() + "\n");
        });
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();
    }
    /**
     * Genereaza un raport cu clientii care au comanda de mai mult decat un anumit numar si de o valoare mai mare decat o valoare data
     *
     * @param nr  numarul minim de comenzi
     * @param val valoarea minima a comenzii
     * @pre nr > 0 and val > 0;
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */


    public void generateReport3(int nr, int val) {
        assert nr > 0 && val > 0;
        StringBuilder s = new StringBuilder();
        List<String> clients = new ArrayList<>();
        s.append("Report: the clients that have ordered more than a specified number of times and the value \n").append("of the order was higher than a specified amount.\n");
        hmap.entrySet().stream().filter(entry -> entry.getKey().getSum() > val).forEach(entry -> users.stream().filter(c -> c.getContor() > nr).forEach(c -> {
            clients.add(String.valueOf(c.getID()));
        }));
        List<String> noDup = clients.stream().distinct().collect(Collectors.toList());
        for (String id : noDup) {
            s.append("Client id: " + id);
        }
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();
    }

    /**
     * Genereaza un raport cu comenzile realizate intr-o anumita zi
     *
     * @param day ziua
     * @pre day != "";
     * @post s-a scris ceva in raport
     * @invariant menu!=null
     */

    @Override
    public void generateReport4(String day) {
        assert !day.equals("");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder s = new StringBuilder();
        List<String> m = new ArrayList<String>();
        int[] nr = new int[menu.size()];
        for (int i = 0; i < nr.length; i++) {
            nr[i] = 0;
        }
        s.append("Report:  the products ordered within a specified day with the number of times they have \n been ordered.\n");
        hmap.entrySet().stream().filter(entry-> formatter.format(entry.getKey().getDate()).equals(day)).forEach(entry->{
            for (MenuItem menuItem : entry.getValue()) {
                m.add(menuItem.getTitle());
            }
        });
        List<String> noDup = m.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < noDup.size(); i++) {
            for (String value : m) {
                if (noDup.get(i).equals(value)) {
                    nr[i]++;
                }
            }
        }
        for (int i = 0; i < noDup.size(); i++) {
            s.append("\n" + noDup.get(i) + " " + nr[i] + "\n");
        }
        FileWriter.write("report.txt", s.toString());
        assert !s.toString().equals("");
        assert isWellFormed();
    }

    /**
     * Creaza o comanda noua
     *
     * @param orderID   id comanda
     * @param orderDate data
     * @param clientID  id client
     * @pre orderID >= 0 and orderDate != null and clientID >= 0;
     * @post lista cu produsele comandate != null
     * @invariant menu!=null
     */


    public void createNewOrder(int orderID, Date orderDate, int clientID) {
        assert orderID >= 0 && orderDate != null && clientID >= 0;
        Order o = new Order(clientID, orderID, orderDate);
        ArrayList<MenuItem> lista = new ArrayList<MenuItem>();
        hmap.put(o, lista);
        assert lista != null;
        assert isWellFormed();
    }

    /**
     * Adauga produsul dorit la comanda
     *
     * @param orderID id comenzii
     * @pre orderID >=0;
     * @post produsul dorit!=null si prosusul a fost adaugat cu succes la comanda
     * @invariant menu!=null
     */

    public void addToOrder(int orderID) {
        assert orderID >= 0;
        int i = 0;
        BaseProduct item = getProduct(interfaces.Client.getTable());
        for (MenuItem menuItem : menu) {
            if (menuItem.getTitle().equals(item.getTitle())) {
                i = menuItem.getNr() + 1;
                menuItem.setNr(i);
                item = (BaseProduct) menuItem;
            }
        }
        for (HashMap.Entry<Order, ArrayList<MenuItem>> entry : hmap.entrySet()) {
            if (entry.getKey().getOrderId() == orderID)
                entry.getValue().add(item);
        }
        assert item != null;
        assert hmap.containsValue(item);
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
    public void createCompositeProduct() {
        compositeProduct = new CompositeProduct();
    }

    public void addToCompositeProduct() {
        BaseProduct baseProduct = getProduct(interfaces.Administrator.getTable());
        compositeProduct.getList().add(baseProduct);
    }


    public void setCompositeProduct(CompositeProduct compositeProduct) {
        this.compositeProduct = compositeProduct;
    }

    public static void setObservers(List<Observer> observers) {
        DeliveryService.observers = observers;
    }

    /**
     * Invariant
     *
     * @return true daca meniu!=null si false in caz contrar
     */

    public boolean isWellFormed() {
        return menu != null;
    }


}
