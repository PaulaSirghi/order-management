package presentationLayer;
import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdministratorView extends JFrame{

    private JButton b1=new JButton("import");
    private JButton b2=new JButton("add");
    private JButton b3=new JButton("delete");
    private JButton b4=new JButton("modify");
    private JButton b5=new JButton("compose");
    private JButton b6=new JButton("report");
    private JButton b7=new JButton("adauga");
    private JButton b8=new JButton("produse");
    private JButton b9=new JButton("inapoi");
    private JComboBox<String> cb=new JComboBox<>();
    private JTextField t1=new JTextField();
    private JTextField t2=new JTextField();
    private JTextField t3=new JTextField();
    private JTextField t4=new JTextField();
    private JTextField t5=new JTextField();
    private JTextField t6=new JTextField();
    private JTextField t7=new JTextField();
    private JTextField t8=new JTextField();
    private JLabel l1=new JLabel("search");
    private JLabel l2=new JLabel("nume nou");
    private JLabel l3=new JLabel("rating");
    private JLabel l4=new JLabel("calories");
    private JLabel l5=new JLabel("protein");
    private JLabel l6=new JLabel("fat");
    private JLabel l7=new JLabel("sodium");
    private JLabel l8=new JLabel("price");
    private  DeliveryService dl;
    private ArrayList<Client> c=new ArrayList<>();
    private ArrayList<MenuItem> produse=new ArrayList<>();
    private MainView m;
    private Serializator s;
    private String sir=new String();
    public static String manageS() {
        return "Modificare realizata cu succes";
    }
    public static String impS() {
        return "Import realizat cu succes";
    }
    public static String rapS() {
        return "Raport realizat cu succes";
    }

    public void comboBoxInit()
    {
        for(MenuItem m:dl.getP()) {
            cb.addItem(m.getTitle());
        }
    }
    public AdministratorView(ArrayList<Client> c,MainView m,Serializator s){
        this.s=s;
        this.m=m;
        this.c=c;
        this.setTitle("Administrator");
        JPanel jos=new JPanel();
        JPanel sus=new JPanel();
        sus.setLayout(new GridLayout(9,2));
        sus.add(l1);sus.add(t1);
        sus.add(l2);sus.add(t2);
        sus.add(l3);sus.add(t3);
        sus.add(l4);sus.add(t4);
        sus.add(l5);sus.add(t5);
        sus.add(l6);sus.add(t6);
        sus.add(l7);sus.add(t7);
        sus.add(l8);sus.add(t8);
        this.m=m;
        jos.add(b1);
        b1.addActionListener(new Import());
        jos.add(b2);
        b2.addActionListener(new Add());
        jos.add(b3);
        b3.addActionListener(new Delete());
        jos.add(b4);
        b4.addActionListener(new Update());
        jos.add(b5);
        b5.addActionListener(new Compose());
        jos.add(b6);
        b6.addActionListener(new Rep());
        jos.add(b7);
        b7.addActionListener(new Adauga());
        jos.add(b8);
        b8.addActionListener(new Produse());
        jos.add(b9);
        b9.addActionListener(new Inapoi());
        JPanel joss=new JPanel();
        joss.add(cb);
        JPanel panou=new JPanel();
        panou.setLayout(new GridLayout(3,1));
        panou.add(sus);
        panou.add(jos);
        panou.add(joss);
        this.setContentPane(panou);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(750,400);
    }
    public void importP() {
        ArrayList<MenuItem>pr=new ArrayList<>();ArrayList<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("products.csv"))) {
            list = (ArrayList<String>) stream
                    .map(String::toUpperCase)
                    .flatMap(string -> Stream.of(string.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sem=7;
        while(sem<list.size()) {
            int ap=1;
            for(MenuItem prod:pr) {
                if(prod.getTitle().equals(list.get(sem))) {
                    ap=0;break;
                }
            }
            if(ap==1) { pr.add(new BaseProduct(list.get(sem),list.get(sem+1),list.get(sem+2),list.get(sem+3),list.get(sem+4),list.get(sem+5),list.get(sem+6))); }
            sem+=7;
        }
        s.getD().setP(pr);ArrayList<MenuItem> m=new ArrayList<>();m.add(s.getD().getP().get(0));
        s.writeC();
        impS();
    }
    public void manageP(int sem,MenuItem m,MenuItem mNou,ArrayList<MenuItem> list,String name) {//add delete update create

        switch (sem)
        {
            case 1: s.getD().getP().add(mNou);break;
            case 2: s.getD().getP().remove(m);break;
            case 3: int j=0;
                for(MenuItem i:s.getD().getP()) {
                    if(i.getTitle().equals(m.getTitle())) {
                        s.getD().getP().get(j).setTitle(mNou.getTitle());s.getD().getP().get(j).setCalories(mNou.getCalories());s.getD().getP().get(j).setFats(mNou.getFats());s.getD().getP().get(j).setPrice(mNou.getPrice());s.getD().getP().get(j).setRating(mNou.getRating());s.getD().getP().get(j).setProtein(mNou.getProtein());s.getD().getP().get(j).setSodium(mNou.getSodium());
                        break;
                    }
                    j++;
                }
                break;
            case 4:
                double rating=0.0; int calories=0, protein=0, fats=0, sodium=0, price=0;
                ArrayList<MenuItem>l=new ArrayList<>();
                for(MenuItem item:list) {
                    l.add(item);
                    rating+=item.getRating();
                    calories+=item.getCalories();protein+=item.getProtein();fats+=item.getFats();sodium+=item.getSodium();price+=item.getPrice();
                }
                CompositeProduct mNouc=new CompositeProduct(name,String.valueOf(rating),String.valueOf(calories),String.valueOf(protein),String.valueOf(fats),String.valueOf(sodium),String.valueOf(price),l);
                s.getD().getP().add(mNouc);
        }
    }
    class Rep implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           AdminReport r=new AdminReport(s);
           rapS();
        }
    }
    class Produse implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dl=new DeliveryService(c,new ArrayList<>());
            try {
                s=new Serializator(dl);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            try {
                dl=s.deserialization();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            try {
                s=new Serializator(dl);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            comboBoxInit();
            s.writeC();
            Controller con=new Controller(m,s);

        }
    }
    class Import implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<MenuItem> pr=new ArrayList<>();
            dl=new DeliveryService(c,pr);
            importP();
            try {
                s=new Serializator(dl);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            comboBoxInit();
            s.writeC();
            Controller con=new Controller(m,s);
            impS();
        }
    }
    class Adauga implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sir= (String) cb.getSelectedItem();
            for(MenuItem item:dl.getP())
            {
                if(item.getTitle().equals(sir))
                {
                    produse.add(item);
                    break;
                }
            }
            s.writeC();
        }
    }
    class Add implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem m=new BaseProduct(t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText(),t8.getText());
            ArrayList<MenuItem> al=new ArrayList<MenuItem>();
            manageP(1,new BaseProduct(),m,al,"");
            s.writeC();
        }
    }
    class Delete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem mInit=new BaseProduct();
            for(MenuItem item:dl.getP()) {
                if(item.getTitle().equals(t1.getText())) {
                    mInit=item;
                    break;
                }
            }
            ArrayList<MenuItem> al=new ArrayList<MenuItem>();
            manageP(2,mInit,new BaseProduct(),al,"");
            s.writeC();
        }
    }
    class Update implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           MenuItem m=new BaseProduct(t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText(),t8.getText());
           MenuItem mInit=new BaseProduct();
           for(MenuItem item:dl.getP()) {
               if(item.getTitle().equals(t1.getText())) {
                   mInit=item;
                   break;
               }
           }
           ArrayList<MenuItem> al=new ArrayList<MenuItem>();
           manageP(3,mInit,m,al,"");
           s.writeC();
           manageS();
        }
    }
    class Compose implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manageP(4,new BaseProduct(),new BaseProduct(),produse,t2.getText());
            produse=new ArrayList<>();
            s.writeC();
            manageS();
        }
    }
    class Inapoi implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           setVisible(false);
        }
    }
}
