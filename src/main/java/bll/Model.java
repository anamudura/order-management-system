package bll;

import javax.swing.*;
import java.util.ArrayList;

public class Model {
    private ArrayList<User> list = new ArrayList<User>();
    public Model()
    {

    }
//    public static void loadUsers(ArrayList<User> users2)
//    {
//        list.addAll(users2);                // de ce trebuie lista sa fie statica pt add all
//    }

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public void addUser(String username, String password, Rol role)
    {
        if(username.equals("") || username.equals(" ") || password.equals("") || password.equals(" "))
        {
            JOptionPane.showMessageDialog(null,
                    "Introducere invalida / nula !",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }
        for(User i: list)
        {
            if(i.getUsername().equals(username))
            {
                JOptionPane.showMessageDialog(null,
                        "Acest username este deja folosit !",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }
        }

        list.add(new User(username,password,role));
    }
    public int checkUser(String username,String password)
    {
        if(username.equals("") || username.equals(" ") || password.equals("") || password.equals(" "))
        {
            JOptionPane.showMessageDialog(null,
                    "Introducere invalida / nula!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);

            return -1;
        }

        for(User i: list)
        {
            if(i.getUsername().equals(username))
            {
                if(!i.getPassword().equals(password))
                {
                    JOptionPane.showMessageDialog(null,
                            "Parola gresita!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);

                    return -1;
                }
                else
                {
                    if(i.getRol() == Rol.ADMINISTRATOR)
                        return 1;                     //1 pentru administrator
                    else
                    {
                        if(i.getRol() == Rol.EMPLOYEE)
                            return 2;        // 2 pentru angajat
                        else
                        {
                            if(i.getRol() == Rol.CLIENT )
                                return 3;        //3 pentru client
                        }
                    }
                }

            }
        }
        JOptionPane.showMessageDialog(null,
                "Parola sau username gresit!",
                "Error!",
                JOptionPane.ERROR_MESSAGE);
        return -1;
    }


    public int getUserID(String username,String password) {
        if(username.equals("") || username.equals(" ") || password.equals("") || password.equals(" "))
        {
            JOptionPane.showMessageDialog(null,
                    "Introducere invalida/ nula!",
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);

            return -1;
        }

        for(User i: list) {
            if (i.getUsername().equals(username)) {
                if (!i.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(null,
                            "Parola gresita!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);

                    return -1;
                }
                else
                {
                    return i.getID();
                }
            }
        }
        return -1;
    }

}
