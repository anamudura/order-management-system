package interfaces;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OrderPreview extends JFrame {

    private JPanel contentPane;
    private JLabel fundal;
    private JButton btnCompleteOrder;
    private JLabel label;

    public OrderPreview() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Order Preview");
        Color color = new Color(70, 50, 45);
        setBounds(100, 100, 505, 323);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnCompleteOrder = new JButton("Complete the Order");
        btnCompleteOrder.setBounds(183, 249, 150, 21);
        btnCompleteOrder.setBackground(color);
        btnCompleteOrder.setForeground(Color.WHITE);
        contentPane.add(btnCompleteOrder);

        label = new JLabel("");
        label.setBounds(20, 20, 380, 215);
        label.setForeground(color);
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        contentPane.add(label);

        fundal = new JLabel("");
       // fundal.setIcon(new ImageIcon(getClass().getClassLoader().getResource("order.jpg")));
        fundal.setBounds(-7, -25, 505, 323);
        contentPane.add(fundal);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void addCompleteOrderAction(ActionListener actionListener) {
        this.btnCompleteOrder.addActionListener(actionListener);
    }
}
