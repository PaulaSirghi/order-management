package businessLayer;

import presentationLayer.EmployeeView;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Order implements Serializable{
    private int orderID;
    private String clientID;
    private SimpleDateFormat format;
    private Date date;

    public Order(int orderID, String clientID, SimpleDateFormat format,Date date) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.format=format;
        this.date=date;
        String s="Comanda plasata de "+clientID;
        EmployeeView e=new EmployeeView();
        e.update(s);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order user = (Order) o;
        return orderID == user.orderID &&
                Objects.equals(clientID, user.clientID) &&
                Objects.equals(format, user.format);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderID,clientID,format);
    }

    public SimpleDateFormat getFormat() {
        return format;
    }

    public Date getDate() {
        return date;
    }

    public String getClientID() {
        return clientID;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }

    public int getOrderID() {
        return orderID;
    }
}
