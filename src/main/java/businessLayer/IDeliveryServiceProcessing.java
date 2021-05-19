/**
 * @pre precondition is an expression
 * @post postcondition is a String
 * */
package businessLayer;

public interface IDeliveryServiceProcessing {
    /**
     * import the products from the given file
     * @pre p!=null
     * @post "Import realizat cu succes"
     * @post @nochange
     * */
    public void importP();
    /**
     * manage the product of the application
     * @pre p!=null;p.size()>0
     * @post "Modificare realizata cu succes"
     * @post @nochange
     * */
    public void manageP();
    /**
     * generate reports
     * @pre DeliveryService!=null
     * @post "Raport realizat cu succes"
     * @post @nochange
     * */
    public void generateR();
    /**
     * create order
     * @pre o!=null;
     * @post "Comanda realizata cu succes"
     * @post @nochange
     * */
    public void createO();
    /**
     * search for products
     * @pre   p!=null;p.size()>0;
     * @post "Cautare realizata cu succes"
     * @post @nochange
     * */
    public void searchP();
}
