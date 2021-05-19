package start;

import businessLayer.Client;
import businessLayer.DeliveryService;
import dataLayer.Serializator;
import presentationLayer.Controller;
import presentationLayer.MainView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[]args) throws IOException {
        MainView m=new MainView();
        ArrayList<Client> c=new ArrayList<>();
        DeliveryService dl=new DeliveryService(c,new ArrayList<>());
       /*Serializator s=new Serializator(dl);


        try {
            s.writeC();
            dl=s.deserialization();
            s.writeC();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
      try {
            Serializator s=new Serializator(dl);
            dl=s.deserialization();
            s=new Serializator(dl);
            s.writeC();
            Controller con=new Controller(m,s);
        } catch (FileNotFoundException fileNotFoundException ) {
            fileNotFoundException.printStackTrace();
        }
        catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
