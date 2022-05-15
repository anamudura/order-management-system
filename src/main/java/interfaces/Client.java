package interfaces;


import bll.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Client extends JFrame {

    private static JTable table;
    private static DefaultTableModel model = new DefaultTableModel();
    private JTextField title;
    private JTextField price;
    private JTextField rating;
    private JTextField fat;
    private JTextField calories;
    private JTextField protein;
    private JTextField sodium;
    private JLabel lblTitle;
    private JLabel lblPrice;
    private JLabel lblFat;
    private JLabel lblRating;
    private JLabel lblCalories;
    private JLabel lblProtein;
    private JLabel lblSodium;
    private JPanel contentPane;
    private JLabel lblTitlu;
    private JButton btnCreateOrder;
    private JButton btnSearch;
    private JButton btnAddToOrder;
    private JButton btnPreviewOrder;

    public Client() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setBounds(100, 100, 753, 511);
        Color color3 = new Color(202, 160, 140);
        Color color1 = new Color(70, 50, 45);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JTextField();
        title.setBounds(20, 23, 80, 19);
        contentPane.add(title);
        title.setColumns(10);

        rating = new JTextField();
        rating.setBounds(110, 23, 80, 19);
        contentPane.add(rating);
        title.setColumns(10);

        price = new JTextField();
        price.setBounds(200, 23, 80, 19);
        contentPane.add(price);
        price.setColumns(10);

        fat = new JTextField();
        fat.setBounds(290, 23, 80, 19);
        contentPane.add(fat);
        fat.setColumns(10);

        calories = new JTextField();
        calories.setBounds(380, 23, 80, 19);
        contentPane.add(calories);
        calories.setColumns(10);

        protein = new JTextField();
        protein.setBounds(470, 23, 80, 19);
        contentPane.add(protein);
        protein.setColumns(10);

        sodium = new JTextField();
        sodium.setBounds(560, 23, 80, 19);
        contentPane.add(sodium);
        sodium.setColumns(10);

        lblTitle = new JLabel("Title");
        lblTitle.setBounds(32, 10, 45, 13);
        lblTitle.setForeground(Color.WHITE);
        contentPane.add(lblTitle);

        lblRating = new JLabel("Rating");
        lblRating.setBounds(110, 10, 45, 13);
        lblRating.setForeground(Color.WHITE);
        contentPane.add(lblRating);

        lblPrice = new JLabel("Price");
        lblPrice.setBounds(200, 10, 45, 13);
        lblPrice.setForeground(Color.WHITE);
        contentPane.add(lblPrice);

        lblFat = new JLabel("Fat");
        lblFat.setBounds(470, 10, 45, 13);
        lblFat.setForeground(Color.WHITE);
        contentPane.add(lblFat);

        lblCalories = new JLabel("Calories");
        lblCalories.setBounds(290, 10, 49, 13);
        lblCalories.setForeground(Color.WHITE);
        contentPane.add(lblCalories);

        lblProtein = new JLabel(" Protein");
        lblProtein.setBounds(380, 10, 45, 13);
        lblProtein.setForeground(Color.WHITE);
        contentPane.add(lblProtein);

        lblSodium = new JLabel("Sodium");
        lblSodium.setBounds(560, 10, 45, 13);
        lblSodium.setForeground(Color.WHITE);
        contentPane.add(lblSodium);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(646, 20, 85, 21);
        btnSearch.setBackground(color1);
        btnSearch.setForeground(Color.WHITE);
        contentPane.add(btnSearch);
        table = new JTable();
        table.setForeground(color1);
        table.setBackground(color3);
        table.setBounds(100, 120, 520, 250);
        String col[] = {"Title", "Rating", "Fat", "Calories", "Protein", "Sodium", "Price"};
        model = new DefaultTableModel(col, 0);
        for (int i = 0; i < DeliveryService.getMenu().size(); i++) {
            String title = DeliveryService.getMenu().get(i).getTitle();
            float rating = DeliveryService.getMenu().get(i).getRating();
            int fats = DeliveryService.getMenu().get(i).getFats();
            int calories = DeliveryService.getMenu().get(i).getCalories();
            int protein = DeliveryService.getMenu().get(i).getProteins();
            int sodium = DeliveryService.getMenu().get(i).getSodium();
            int price = (int) DeliveryService.getMenu().get(i).getPrice();
            Object[] data = {title, rating, fats, calories, protein, sodium, price};
            model.addRow(data);
        }
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(195);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);
        table.getColumnModel().getColumn(4).setPreferredWidth(15);
        table.getColumnModel().getColumn(5).setPreferredWidth(25);
        table.getColumnModel().getColumn(6).setPreferredWidth(5);
        add(table);
        JScrollPane jp = new JScrollPane(table);
        jp.setLocation(100, 120);
        jp.setSize(530, 260);
        add(jp);

        btnCreateOrder = new JButton("Create Order");
        btnCreateOrder.setBounds(125, 395, 153, 21);
        btnCreateOrder.setBackground(color1);
        btnCreateOrder.setForeground(Color.WHITE);
        contentPane.add(btnCreateOrder);

        btnAddToOrder = new JButton("Add to Order");
        btnAddToOrder.setBounds(288, 395, 153, 21);
        btnAddToOrder.setBackground(color1);
        btnAddToOrder.setForeground(Color.WHITE);
        contentPane.add(btnAddToOrder);

        btnPreviewOrder = new JButton("Order Preview");
        btnPreviewOrder.setBounds(451, 395, 153, 21);
        btnPreviewOrder.setBackground(color1);
        btnPreviewOrder.setForeground(Color.WHITE);
        contentPane.add(btnPreviewOrder);

        JLabel fundal = new JLabel("");
        //fundal.setIcon(new ImageIcon(getClass().getClassLoader().getResource("clientbk.png")));
        fundal.setBounds(-2, -2, 753, 511);
        add(fundal);
    }

    public static JTable getTable() {
        return table;
    }

    public static DefaultTableModel getModel() {
        return model;
    }

    public static void setModel(DefaultTableModel model) {
        Client.model = model;
    }

    public void addSearchAction(ActionListener actionListener) {
        this.btnSearch.addActionListener(actionListener);
    }

    public void addCreateOrderAction(ActionListener actionListener) {
        this.btnCreateOrder.addActionListener(actionListener);
    }

    public void addToOrderAction(ActionListener actionListener) {
        this.btnAddToOrder.addActionListener(actionListener);
    }

    public void addPreviewOrderAction(ActionListener actionListener) {
        this.btnPreviewOrder.addActionListener(actionListener);
    }

    public JTextField getCaloriesC() {
        return calories;
    }

    public JTextField getFatC() {
        return fat;
    }

    public JTextField getPriceC() {
        return price;
    }

    public JTextField getProteinC() {
        return protein;
    }

    public JTextField getSodiumC() {
        return sodium;
    }

    public JTextField getTitleC() {
        return title;
    }

    public JTextField getRatingC() {
        return rating;
    }
}
