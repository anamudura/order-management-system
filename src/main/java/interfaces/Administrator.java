package interfaces;


import bll.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class Administrator extends JFrame {

    private static JTable table;
    private JPanel contentPane;
    private JTextField title;
    private JTextField price;
    private JTextField fat;
    private JTextField calories;
    private JTextField protein;
    private JTextField sodium;
    private JTextField startHour;
    private JTextField endHour;
    private JTextField numberOfOrders;
    private JTextField value;
    private JTextField day;
    private JLabel administratorLabel;
    private JLabel lblTitle;
    private JLabel lblPrice;
    private JLabel lblFat;
    private JLabel lblCalories;
    private JLabel lblProtein;
    private JLabel lblSodium;
    private JLabel lblStartHour;
    private JLabel lblEndHour;
    private JLabel lblNumberOfOrders;
    private JLabel lblValue;
    private JLabel lblDay;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnReport1;
    private JButton btnCreateComposite;
    private JButton btnAddToComposite;
    private JButton btnAddComplete;
    private JButton btnReport2;
    private JButton btnReport3;
    private JButton btnReport4;


    public Administrator() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 753, 511);
        setTitle("Administrator");
        contentPane = new JPanel();
        Color color1 = new Color(105, 79, 63);
        Color color2 = new Color(244, 173, 51);
        Color color3 = new Color(227, 157, 197);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JTextField();
        title.setBounds(20, 78, 96, 19);
        contentPane.add(title);
        title.setColumns(10);

        day = new JTextField();
        day.setBounds(20, 142, 96, 19);
        contentPane.add(day);
        day.setColumns(10);

        price = new JTextField();
        price.setBounds(131, 78, 96, 19);
        contentPane.add(price);
        price.setColumns(10);

        startHour = new JTextField();
        startHour.setBounds(131, 142, 96, 19);
        contentPane.add(startHour);
        startHour.setColumns(10);

        endHour = new JTextField();
        endHour.setBounds(258, 142, 96, 19);
        contentPane.add(endHour);
        endHour.setColumns(10);

        numberOfOrders = new JTextField();
        numberOfOrders.setBounds(393, 142, 96, 19);
        contentPane.add(numberOfOrders);
        numberOfOrders.setColumns(10);

        value = new JTextField();
        value.setBounds(515, 142, 96, 19);
        contentPane.add(value);
        value.setColumns(10);


        fat = new JTextField();
        fat.setBounds(258, 78, 96, 19);
        contentPane.add(fat);
        fat.setColumns(10);

        calories = new JTextField();
        calories.setBounds(393, 78, 96, 19);
        contentPane.add(calories);
        calories.setColumns(10);

        protein = new JTextField();
        protein.setBounds(515, 78, 96, 19);
        contentPane.add(protein);
        protein.setColumns(10);

        sodium = new JTextField();
        sodium.setBounds(633, 78, 96, 19);
        contentPane.add(sodium);
        sodium.setColumns(10);

        lblTitle = new JLabel("Title");
        lblTitle.setBounds(32, 66, 45, 13);
        lblTitle.setForeground(Color.BLACK);
        contentPane.add(lblTitle);

        lblDay = new JLabel("Day");
        lblDay.setBounds(32, 128, 45, 13);
        lblDay.setForeground(Color.BLACK);
        contentPane.add(lblDay);


        lblPrice = new JLabel("Price");
        lblPrice.setBounds(153, 66, 45, 13);
        lblPrice.setForeground(Color.BLACK);
        contentPane.add(lblPrice);


        lblStartHour = new JLabel("Start Hour");
        lblStartHour.setBounds(153, 128, 70, 13);
        lblStartHour.setForeground(Color.BLACK);
        contentPane.add(lblStartHour);

        lblEndHour = new JLabel("End Hour");
        lblEndHour.setBounds(268, 128, 120, 13);
        lblEndHour.setForeground(Color.BLACK);
        contentPane.add(lblEndHour);

        lblNumberOfOrders = new JLabel("Number of Orders");
        lblNumberOfOrders.setBounds(393, 128, 120, 13);
        lblNumberOfOrders.setForeground(Color.BLACK);
        contentPane.add(lblNumberOfOrders);

        lblValue = new JLabel("Value");
        lblValue.setBounds(540, 128, 120, 13);
        lblValue.setForeground(Color.BLACK);
        contentPane.add(lblValue);

        lblFat = new JLabel("Fat");
        lblFat.setBounds(285, 66, 45, 13);
        lblFat.setForeground(Color.BLACK);
        contentPane.add(lblFat);

        lblCalories = new JLabel("Calories");
        lblCalories.setBounds(420, 66, 49, 13);
        lblCalories.setForeground(Color.BLACK);
        contentPane.add(lblCalories);

        lblProtein = new JLabel(" Protein");
        lblProtein.setBounds(540, 66, 45, 13);
        lblProtein.setForeground(Color.BLACK);
        contentPane.add(lblProtein);

        lblSodium = new JLabel("Sodium");
        lblSodium.setBounds(654, 66, 45, 13);
        lblSodium.setForeground(Color.BLACK);
        contentPane.add(lblSodium);

        administratorLabel = new JLabel("Administrator");
        administratorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        administratorLabel.setBounds(315, 23, 120, 35);
        administratorLabel.setForeground(Color.BLACK);
        contentPane.add(administratorLabel);

        btnCreateComposite = new JButton("Create Composite Product");
        btnCreateComposite.setBounds(20, 416, 200, 21);
        btnCreateComposite.setBackground(color1);
        btnCreateComposite.setForeground(Color.BLACK);
        contentPane.add(btnCreateComposite);

        btnAddToComposite = new JButton("Add to Composite Product");
        btnAddToComposite.setBounds(230, 416, 200, 21);
        btnAddToComposite.setBackground(color1);
        btnAddToComposite.setForeground(Color.BLACK);
        contentPane.add(btnAddToComposite);

        btnAddComplete = new JButton("Complete");
        btnAddComplete.setBounds(440, 416, 200, 21);
        btnAddComplete.setBackground(color1);
        btnAddComplete.setForeground(Color.BLACK);
        contentPane.add(btnAddComplete);


        btnAdd = new JButton("Add");
        btnAdd.setBounds(334, 105, 85, 21);
        btnAdd.setBackground(color1);
        btnAdd.setForeground(Color.BLACK);
        contentPane.add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(640, 285, 85, 21);
        btnEdit.setBackground(color1);
        btnEdit.setForeground(Color.BLACK);
        contentPane.add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(640, 316, 85, 21);
        btnDelete.setBackground(color1);
        btnDelete.setForeground(Color.BLACK);
        contentPane.add(btnDelete);

        btnReport1 = new JButton(" Report1");
        btnReport1.setBounds(195, 175, 85, 21);
        btnReport1.setBackground(color1);
        btnReport1.setForeground(Color.BLACK);
        contentPane.add(btnReport1);

        btnReport2 = new JButton(" Report2");
        btnReport2.setBounds(286, 175, 85, 21);
        btnReport2.setBackground(color1);
        btnReport2.setForeground(Color.BLACK);
        contentPane.add(btnReport2);

        btnReport3 = new JButton(" Report3");
        btnReport3.setBounds(381, 175, 85, 21);
        btnReport3.setBackground(color1);
        btnReport3.setForeground(Color.BLACK);
        contentPane.add(btnReport3);

        btnReport4 = new JButton(" Report4");
        btnReport4.setBounds(472, 175, 85, 21);
        btnReport4.setBackground(color1);
        btnReport4.setForeground(Color.BLACK);
        contentPane.add(btnReport4);


        String col[] = {"Title", "Rating", "Fat", "Calories", "Protein", "Sodium", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (int i = 0; i < DeliveryService.getMenu().size(); i++) {
            String title = DeliveryService.getMenu().get(i).getTitle();
            float rating = DeliveryService.getMenu().get(i).getRating();
            int fats = DeliveryService.getMenu().get(i).getFats();
            int calories = DeliveryService.getMenu().get(i).getCalories();
            int protein = DeliveryService.getMenu().get(i).getProteins();
            int sodium = DeliveryService.getMenu().get(i).getSodium();
            int price = (int) DeliveryService.getMenu().get(i).getPrice();
            Object[] data = {title, rating, fats, calories, protein, sodium, price};
            tableModel.addRow(data);
        }
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(260);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(25);
        table.getColumnModel().getColumn(5).setPreferredWidth(30);
        table.getColumnModel().getColumn(6).setPreferredWidth(5);
        table.setBounds(30, 225, 600, 190);
        table.setBackground(color1);
        table.setForeground(Color.WHITE);
        table.setFillsViewportHeight(true);

        contentPane.add(table);
        JScrollPane jp2 = new JScrollPane(table);
        jp2.setLocation(30, 216);
        jp2.setSize(600, 190);
        add(jp2);
        JLabel fundal = new JLabel("");
        add(fundal);
    }

    public static JTable getTable() {
        return table;
    }

    public void addCreateCompositeAction(ActionListener actionListener) {
        this.btnCreateComposite.addActionListener(actionListener);
    }

    public void addToCompositeAction(ActionListener actionListener) {
        this.btnAddToComposite.addActionListener(actionListener);
    }

    public void addCompleteAction(ActionListener actionListener) {
        this.btnAddComplete.addActionListener(actionListener);
    }

    public void addDeleteAction(ActionListener actionListener) {
        this.btnDelete.addActionListener(actionListener);
    }

    public void addEditAction(ActionListener actionListener) {
        this.btnEdit.addActionListener(actionListener);
    }

    public void addAddAction(ActionListener actionListener) {
        this.btnAdd.addActionListener(actionListener);
    }

    public void addRaport1Action(ActionListener actionListener) {
        this.btnReport1.addActionListener(actionListener);
    }

    public void addRaport2Action(ActionListener actionListener) {
        this.btnReport2.addActionListener(actionListener);
    }

    public void addRaport3Action(ActionListener actionListener) {
        this.btnReport3.addActionListener(actionListener);
    }

    public void addRaport4Action(ActionListener actionListener) {
        this.btnReport4.addActionListener(actionListener);
    }

    public JTextField getSodiumA() {
        return sodium;
    }

    public JTextField getProteinA() {
        return protein;
    }

    public JTextField getFatA() {
        return fat;
    }

    public JTextField getCaloriesA() {
        return calories;
    }

    public JTextField getPriceA() {
        return price;
    }

    public JTextField getTitleA() {
        return title;
    }

    public JTextField getStartHour() {
        return startHour;
    }

    public JTextField getEndHour() {
        return endHour;
    }

    public JTextField getDay() {
        return day;
    }

    public JTextField getValue() {
        return value;
    }

    public JTextField getNumberOfOrders() {
        return numberOfOrders;
    }
}
