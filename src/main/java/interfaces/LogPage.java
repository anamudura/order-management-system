package interfaces;

import dataLayer.Serializator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogPage extends JFrame {
    private JPanel contentPane;
    private JTextField user;
    private JPasswordField pass;
    private JFrame frame = new JFrame();
    private JButton buttonLogin;
    private JButton buttonRegister;

    public LogPage() {
        Color color = new Color(91, 82, 77);
        frame.setTitle("Login Page");
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                Serializator.serializeData();
            }

        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 391, 343);
        contentPane = new JPanel();
        contentPane.setBackground(color);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        //username label
        JLabel labelUsername = new JLabel("Username");
        //labelUsername.setIcon(new ImageIcon(getClass().getClassLoader().getResource("rsz_male-icon-7928.png")));
        labelUsername.setBackground(new Color(0, 61, 77));
        labelUsername.setForeground(Color.WHITE);
        labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelUsername.setBounds(26, 68, 115, 36);
        contentPane.add(labelUsername);

        //username text field
        user = new JTextField();
        user.setForeground(Color.WHITE);
        user.setBackground(color);
        user.setFont(new Font("Tahoma", Font.PLAIN, 15));
        user.setBounds(26, 102, 327, 36);
        contentPane.add(user);
        user.setColumns(10);

        //password label
        JLabel labelPassword = new JLabel("Password");
        //labelPassword.setIcon(new ImageIcon(getClass().getClassLoader().getResource("rsz_1024px-passwordsvg.png")));
        labelPassword.setForeground(Color.WHITE);
        labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelPassword.setBounds(26, 148, 145, 33);
        contentPane.add(labelPassword);

        //password field
        pass = new JPasswordField();
        pass.setForeground(Color.WHITE);
        pass.setBackground(color);
        pass.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pass.setBounds(26, 183, 327, 38);
        contentPane.add(pass);

        //button de Login
        buttonLogin = new JButton("Login");
        buttonLogin.setForeground(SystemColor.controlLtHighlight);
        buttonLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonLogin.setBackground(color);
        buttonLogin.setBounds(70, 247, 109, 29);
        contentPane.add(buttonLogin);

        buttonRegister = new JButton("Register");
        buttonRegister.setForeground(SystemColor.controlLtHighlight);
        buttonRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonRegister.setBackground(color);
        buttonRegister.setBounds(190, 247, 109, 29);
        contentPane.add(buttonRegister);

        //Login Page label si panel
        JLabel lblNewLabel = new JLabel("Login Page");
        lblNewLabel.setBounds(116, 0, 150, 62);
        contentPane.add(lblNewLabel);
        lblNewLabel.setIcon(null);
        lblNewLabel.setForeground(SystemColor.controlLtHighlight);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(0, 0, 391, 343);
        contentPane.add(lblNewLabel_1);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void addLogAction(ActionListener actionListener) {
        this.buttonLogin.addActionListener(actionListener);
    }

    public void addRegisterAction(ActionListener actionListener) {
        this.buttonRegister.addActionListener(actionListener);
    }

    public JPasswordField getPass() {
        return pass;
    }

    public JTextField getUser() {
        return user;
    }

}
