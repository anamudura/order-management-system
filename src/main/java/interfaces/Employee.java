package interfaces;

import bll.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Employee extends JFrame implements Observer {
    StringBuilder stringBuilder = new StringBuilder();
    private JPanel contentPane;
    private JLabel label;
    private JLabel fundal;

    public Employee() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Employee");
        Color color = new Color(70, 50, 45);
        setBounds(100, 100, 505, 323);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        label = new JLabel("");
        label.setBounds(20, 20, 380, 215);
        label.setForeground(color);
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        contentPane.add(label);

        fundal = new JLabel("");
        //fundal.setIcon(new ImageIcon(getClass().getClassLoader().getResource("order.jpg")));
         fundal.setBounds(-7, -25, 505, 323);
         contentPane.add(fundal);
    }

    @Override
    public void update(DeliveryService deliveryService) {
        stringBuilder.append("<html>-New order-<br/>");
        label.setText(stringBuilder.toString());

    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
