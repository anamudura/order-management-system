package bll;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Order implements Serializable {
    private int clientId;
    private int orderId;
    private Date date;
    private int sum;
    public Order(int clientId,int orderId,Date date)
    {
        this.clientId = clientId;
        this.orderId = orderId;
        this.date = date;
        setSum(0);

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public int Hash()
    {
        return date.hashCode() + orderId + parseInt(String.valueOf(clientId));
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order))
            return false;
        return ((Order) o).getOrderId() == this.orderId;
    }
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return "Order{" +
                "orderID=" + orderId +
                ", OrderDate=" + formatter.format(date) +
                ", clientID=" + clientId +
                '}' + "\n";
    }
}
