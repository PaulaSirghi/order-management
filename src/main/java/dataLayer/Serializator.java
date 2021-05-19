package dataLayer;

import java.io.*;
import businessLayer.Client;
import businessLayer.DeliveryService;


public class Serializator implements Serializable{
    private FileInputStream file;
    private FileOutputStream fileOut;
    private DeliveryService d;
    private int sem;
    public Serializator(DeliveryService D) throws FileNotFoundException {
        sem=0;
        this.file = new FileInputStream
                ("file.txt");
        this.fileOut =
                new FileOutputStream("file.txt",true);
        this.d=D;
    }
    public void writeC()
    {
        try {
            if(sem==1) {
                this.fileOut =
                        new FileOutputStream("file.txt");
            }
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(d);
            out.close();
            System.out.printf("Serialized data is saved in file.txt");
            fileOut.close();
            sem=1;
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public DeliveryService deserialization() throws ClassNotFoundException {
        DeliveryService object = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.txt"));
            object = (DeliveryService) in.readObject();
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Object has been deserialized and it is defined by:");
        for(Client c:object.getC())
        {
            System.out.println("name= "+c.getNume()+" and password= "+c.getParola());

        }
        return object;
    }

    public DeliveryService getD() {
        return d;
    }

    public void setD(DeliveryService d) {
        this.d = d;
    }
}
