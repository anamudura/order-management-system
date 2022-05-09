package dataLayer;

import bll.DeliveryService;
import bll.MenuItem;
import bll.Order;
import bll.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Serializator {
    public static void serializeData() {
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(DeliveryService.getMenu());
        data.add(DeliveryService.getUsers());
        data.add(DeliveryService.getHmap());
        try {
            FileOutputStream fileOut = new FileOutputStream("data.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in data.txt");
        } catch (IOException exception) {
            exception.printStackTrace();

        }
    }

    public static void deserializeData() {
        ArrayList<Object> deserialized = new ArrayList<Object>();
        try {
            FileInputStream fileIn = new FileInputStream("data.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserialized = (ArrayList<Object>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        DeliveryService.setMenu((ArrayList<MenuItem>) deserialized.get(0));
        DeliveryService.setUsers((ArrayList<User>) deserialized.get(1));
        DeliveryService.setHmap((HashMap<Order, ArrayList<MenuItem>>) deserialized.get(2));
    }
}
