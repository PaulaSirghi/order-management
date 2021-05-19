/**
 * @invariant isWellFormed()
 * */
package businessLayer;
import presentationLayer.AdministratorView;
import presentationLayer.ClientView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class DeliveryService extends Observable implements IDeliveryServiceProcessing , Serializable {
    ArrayList<Client> c=new ArrayList<>();
    ArrayList<MenuItem> p=new ArrayList<>();
    HashMap<Order,ArrayList<MenuItem>> orders=new HashMap();
    protected boolean isWellFormed(){
        return this!=null;
    }
    public DeliveryService(ArrayList<Client> c,ArrayList<MenuItem>p) {
        this.c = c;this.p = p;
        assert isWellFormed();
    }
    public ArrayList<Client> getC() {
        return c;
    }
    public void setC(ArrayList<Client> c) {
        this.c = c;
    }
    public ArrayList<MenuItem> getP() {
        return p;
    }
    public void setP(ArrayList<MenuItem> p) {
        this.p = p;
    }
    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }
    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    @Override
    public void importP() {
        assert this!=null;
        assert p!=null;
        String s= AdministratorView.impS();
        System.out.println(s);
        assert s.equals("Import realizat cu succes");
        assert isWellFormed();
    }
    @Override
    public void manageP() {//add delete update create
            assert this!=null;
            assert p!=null;
            assert p.size()>0;
            String s= AdministratorView.manageS();
        System.out.println(s);
        assert s.equals("Modificare realizata cu succes");
        assert isWellFormed();
    }
    @Override
    public void generateR() {
        assert this!=null;
        assert p!=null;
        assert p.size()>1;
        String s= AdministratorView.rapS();
        System.out.println(s);
        assert s.equals("Raport realizat cu succes");
        assert isWellFormed();
    }
    @Override
    public void createO() {
        assert this!=null;
        assert orders!=null;
        assert orders.size()>0;
        String s= ClientView.orderS();
        System.out.println(s);
        assert s.equals("Comanda realizat cu succes");
        assert isWellFormed();
    }

    @Override
    public void searchP() {
        assert this!=null;
        assert p!=null;
        assert p.size()>0;
        String s= ClientView.seaS();
        System.out.println(s);
        assert s.equals("Cautare realizata cu succes");
        assert isWellFormed();
    }
}
