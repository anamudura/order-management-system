package interfaces;

import bll.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Controller {
    private static Administrator administrator = new Administrator();
    private static Employee em = new Employee();
    private int orderId2 = 0;
    private LogPage loginPage = new LogPage();
    private Client client1 = new Client();
    private dataLayer.FileWriter fileWriter;
    private StringBuilder text;
    private OrderPreview orderPreview = new OrderPreview();
    private CompositeProduct compositeProduct;
    private int id = 0;

    public Controller(DeliveryService deliveryService) {
        loginPage.addLogAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = 0;
                for (User user : deliveryService.getUsers()) {
                    if (loginPage.getUser().getText().equals(user.getUsername()) && loginPage.getPass().getText().equals(user.getPassword())) {
                        System.out.println(user.getUsername());
                        ok = 1;
                        if (user.getRol().equals(Rol.ADMINISTRATOR)) {
                            administrator.setVisible(true);
                        }
                        if (user.getRol().equals(Rol.EMPLOYEE)) {
                            user.setRol(Rol.EMPLOYEE);
                            deliveryService.registerObserver(em);
                            em.setVisible(true);
                        }
                        if (user.getRol().equals(Rol.CLIENT)) {
                            id = user.getID();
                            client1.setVisible(true);
                        }
                    }

                }
                if (ok == 0) JOptionPane.showMessageDialog(loginPage, "Wrong user or pass!");
            }
        });


        loginPage.addRegisterAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = 0;
                for (User user : deliveryService.getUsers()) {
                    if (user.getUsername().equals(loginPage.getUser().getText())) {
                        ok = 1;
                        JOptionPane.showMessageDialog(loginPage, "This username already exists!");
                    }
                }
                if (ok == 0) {
                    Random rand = new Random();
                    int id1 = rand.nextInt(100);
                    deliveryService.getUsers().add(new bll.User(loginPage.getUser().getText(), loginPage.getPass().getText(), Rol.CLIENT));
                    JOptionPane.showMessageDialog(loginPage, "Succes!");
                }
            }
        });

        client1.addSearchAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titlu = client1.getTitleC().getText();
                String rating = client1.getRatingC().getText();
                String fats = client1.getFatC().getText();
                String calories = client1.getCaloriesC().getText();
                String protein = client1.getProteinC().getText();
                String sodium = client1.getSodiumC().getText();
                String price = client1.getPriceC().getText();
                deliveryService.searchProduct(titlu, rating, fats, calories, protein, sodium, price);

            }
        });

        client1.addCreateOrderAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int orderId = rand.nextInt(100);
                orderId2 = orderId;
                Date date = new Date();
                deliveryService.createNewOrder(orderId, date, id);
            }
        });
        client1.addToOrderAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.addToOrder(orderId2);
            }
        });
        client1.addPreviewOrderAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (HashMap.Entry<Order, ArrayList<MenuItem>> entry : deliveryService.getHmap().entrySet()) {
                    if (entry.getKey().getOrderId() == orderId2) {
                        compositeProduct = new CompositeProduct();
                        compositeProduct.setList(entry.getValue());
                        compositeProduct.computePrice();
                        entry.getKey().setSum((int) compositeProduct.getPrice());
                    }
                }
                text = new StringBuilder();
                StringBuilder order = new StringBuilder();
                order.append("<html>");
                for (MenuItem menuItem : compositeProduct.getList()) {
                    text.append(menuItem.getTitle() + " " + menuItem.getPrice() + "\n");
                    order.append(menuItem.getTitle() + "   " + menuItem.getPrice() + "<br/>");
                }
                text.append("\nTotal: " + compositeProduct.getPrice());
                order.append("<br/> <br/> Total: " + compositeProduct.getPrice() + "</html>");
                orderPreview.getLabel().setText(order.toString());
                orderPreview.setVisible(true);
            }
        });
        orderPreview.addCompleteOrderAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileWriter.write("factura.txt", "Bill\n" + text.toString());
                for (User client : deliveryService.getUsers()) {
                    if (client.getID() == id) {
                        int orders = client.getContor() + 1;
                        client.setContor(orders);
                    }
                }
                deliveryService.notifyObservers(deliveryService);
                orderPreview.dispose();
            }

        });

        administrator.addCreateCompositeAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.createCompositeProduct();
            }
        });
        administrator.addToCompositeAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.addToCompositeProduct();
            }
        });

        administrator.addCompleteAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.completeCompositeProduct(administrator.getTitleA().getText());
                String col[] = {"Title", "Rating", "Fat", "Calories", "Protein", "Sodium", "Price"};
                DefaultTableModel model = new DefaultTableModel(col, 0);
                DeliveryService.getMenu().stream().forEach(menuItem -> {
                    String t = menuItem.getTitle();
                    float r = menuItem.getRating();
                    int f = menuItem.getFats();
                    int c = menuItem.getCalories();
                    int p = menuItem.getProteins();
                    int s = menuItem.getSodium();
                    int pr = (int) menuItem.getPrice();
                    Object[] data = {t, r, f, c, p, s, pr};
                    model.addRow(data);
                });
                Administrator.getTable().setModel(model);
                deliveryService.doColumnsResize(Administrator.getTable());

            }
        });
        administrator.addDeleteAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = interfaces.Administrator.getTable().getModel().getValueAt(interfaces.Administrator.getTable().getSelectedRow(), 0).toString();
                deliveryService.deleteProducts(title);
            }
        });

        administrator.addEditAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.editProducts();
            }
        });
        administrator.addAddAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = administrator.getTitleA().getText();
                float rating = 0;
                int fats = Integer.parseInt(administrator.getFatA().getText());
                int calories = Integer.parseInt(administrator.getCaloriesA().getText());
                int protein = Integer.parseInt(administrator.getProteinA().getText());
                int sodium = Integer.parseInt(administrator.getSodiumA().getText());
                int price = Integer.parseInt(administrator.getPriceA().getText());
                BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fats, sodium, price);
                deliveryService.importProducts(baseProduct);
            }
        });

        administrator.addRaport1Action(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.generateReport1(Integer.parseInt(administrator.getStartHour().getText()), Integer.parseInt(administrator.getEndHour().getText()));
            }
        });
        administrator.addRaport2Action(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nr = Integer.parseInt(administrator.getNumberOfOrders().getText());
                deliveryService.generateReport2(nr);
            }
        });
        administrator.addRaport3Action(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nr = Integer.parseInt(administrator.getNumberOfOrders().getText());
                int val = Integer.parseInt(administrator.getValue().getText());
                deliveryService.generateReport3(nr, val);
            }
        });

        administrator.addRaport4Action(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String day = administrator.getDay().getText();
                deliveryService.generateReport4(day);
            }
        });
    }

    public static Administrator getAdministrator() {
        return administrator;
    }
}
