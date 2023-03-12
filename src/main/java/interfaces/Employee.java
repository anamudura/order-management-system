package interfaces;

import bll.DeliveryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Employee extends JFrame implements Observer {
    StringBuilder stringBuilder = new StringBuilder();
    private JPanel contentPane;
    private JTextField label;
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
        label = new JTextField("");
        label.setBounds(20, 20, 380, 215);
        label.setForeground(color);
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        contentPane.add(label);

        fundal = new JLabel("");
        fundal.setBounds(-7, -25, 505, 323);
        contentPane.add(fundal);
    }

    @Override
    public void update(DeliveryService deliveryService) {
        stringBuilder.append("New order yay\n");
        label.setText(stringBuilder.toString());

    }

    public JTextField getLabel() {
        return label;
    }

    public void setLabel(JTextField label) {
        this.label = label;
    }
}
