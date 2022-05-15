import bll.BaseProduct;
import bll.DeliveryService;
import dataLayer.Serializator;
import interfaces.Controller;

public class Main {


    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();
        BaseProduct b = new BaseProduct();
        BaseProduct.ReadFile();
        Controller controller = new Controller (deliveryService);
    }
}
