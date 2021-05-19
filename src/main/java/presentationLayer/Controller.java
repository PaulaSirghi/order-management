/**

 * */
package presentationLayer;

import businessLayer.Client;
import businessLayer.DeliveryService;
import dataLayer.Serializator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
    private String nume,parola,rol;
    private MainView m;
    private Serializator s;
    public Controller(MainView m,Serializator s) {
        this.m=m;
        this.s=s;
        m.login(new LoginListener());
        m.register(new RegisterListener());
    }
    public void getData() {
        this.nume=m.getT1().getText();
        this.parola=m.getT2().getText();
        this.rol=m.getT3().getText();
    }
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getData();
            Client c=new Client(nume,parola,rol);
            DeliveryService toCompare= null;
            try {
                toCompare = s.deserialization();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            int sem=0;
            for(Client toC:toCompare.getC()) {
                System.out.println(c.getNume()+" "+c.getParola()+" se compara cu "+toC.getNume()+" "+toC.getParola());
                if(toC.getNume().equals(c.getNume()) && toC.getParola().equals(c.getParola())) {
                    sem=1;
                    rol=toC.getRol();
                    break;
                }
            }
            if(sem==1) {
                System.out.println("utilizator existent");
                if(rol.equals("administrator")){
                    AdministratorView a=new AdministratorView(s.getD().getC(),m,s);
                }
                else {
                        ClientView cl=new ClientView(m.getT1().getText(),s);
                }
            }
            else System.out.println("utilizator inexistent");

        }
    }
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getData();
            Client c=new Client(nume,parola,rol);
            DeliveryService toCompare= null;
            try {
                toCompare = s.deserialization();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            int sem=1;
            for(Client cl:toCompare.getC())
            {
                if(cl.getNume().equals(c.getNume())) {
                    sem=0;
                    break;
                }
            }
            if(sem==1) {
                s.getD().getC().add(c);
                s.writeC();
            }
        }
    }
}
