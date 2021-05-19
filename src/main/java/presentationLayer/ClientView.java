package presentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.FileWriter;
import dataLayer.Serializator;
import static java.util.stream.Collectors.toList;

public class ClientView extends JFrame {
    private JButton b1=new JButton("produse");
    private JButton b2=new JButton("cauta");
    private JButton b3=new JButton("comanda");
    private JButton b4=new JButton("adauga");
    private JButton b5=new JButton("inapoi");
    private JTextField t=new JTextField();
    private JLabel l=new JLabel("criterii");
    private JComboBox<String> produse=new JComboBox<>();
    private ArrayList<MenuItem> m=new ArrayList<>();
    private String nume;
    private Serializator s;

    public static String orderS() {
        return "Comanda realizata cu succes";
    }
    public static String seaS() {
        return "Cautare realizata cu succes";
    }
    public ClientView(String nume,Serializator s)
    {
        this.setTitle("Client");
        this.nume=nume;
        t.setColumns(10);
        JPanel sus=new JPanel();
        sus.setLayout(new GridLayout(3,2));
        sus.add(l);
        sus.add(t);
        sus.add(produse);
        JPanel jos=new JPanel();
        JPanel panou=new JPanel();
        jos.add(b1);
        b1.addActionListener(new ViewProducts());
        jos.add(b2);
        b2.addActionListener(new SearchListener());
        jos.add(b3);
        b3.addActionListener(new ComandaListener());
        jos.add(b4);
        b4.addActionListener(new AddListener());
        jos.add(b5);
        b5.addActionListener(new Inapoi());
        panou.add(sus);
        panou.add(jos);
        this.setContentPane(panou);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800,200);
        this.s=s;
    }

    public String getNume() {
        return nume;
    }

    public JTextField getT() {
        return t;
    }

    public void setT(JTextField t) {
        this.t = t;
    }

    public JComboBox<String> getProduse() {
        return produse;
    }

    public ArrayList<MenuItem> getM() {
        return m;
    }

    class SearchListener implements ActionListener {
        public ArrayList<MenuItem> cuv(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = m.getTitle();
                ArrayList<String>list1=new ArrayList<String>();
                list1.add(list.get(0).getTitle());
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.contains(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }

        public ArrayList<MenuItem> rating(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = String.valueOf(m.getRating());ArrayList<String>list1=new ArrayList<String>();list1.add(String.valueOf(list.get(0).getRating()));
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.equals(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }

        public ArrayList<MenuItem> calories(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = String.valueOf(m.getCalories());ArrayList<String>list1=new ArrayList<String>();list1.add(String.valueOf(list.get(0).getCalories()));
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.equals(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }

        public ArrayList<MenuItem> protein(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = String.valueOf(m.getProtein());ArrayList<String>list1=new ArrayList<String>();list1.add(String.valueOf(list.get(0).getProtein()));
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.equals(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }
        public ArrayList<MenuItem> fat(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = String.valueOf(m.getFats());ArrayList<String>list1=new ArrayList<String>();list1.add(String.valueOf(list.get(0).getFats()));
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.equals(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }
        public ArrayList<MenuItem> sodium(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = String.valueOf(m.getSodium());ArrayList<String>list1=new ArrayList<String>();list1.add(String.valueOf(list.get(0).getSodium()));
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.equals(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }
        public ArrayList<MenuItem> price(ArrayList<MenuItem>list,String sir) {
            ArrayList<MenuItem> listaN=new ArrayList<MenuItem>();
            for(MenuItem m:list) {
                String sirulet = String.valueOf(m.getPrice());ArrayList<String>list1=new ArrayList<String>();list1.add(String.valueOf(list.get(0).getPrice()));
                boolean cont= list1.stream().anyMatch(s1 -> sirulet.equals(sir));
                if(cont==true) {
                    listaN.add(m);
                }
            }
            return listaN;
        }
        public ArrayList<MenuItem> cazuri(ArrayList<MenuItem>lista,ArrayList<String>list) {
            for(String s:list) {
                String[] sir;
                sir=s.split(" ");
                switch (sir[0]) {
                    case "Title": ArrayList<MenuItem> listaA=cuv(lista,sir[1]);lista=listaA;break;
                    case "Rating": ArrayList<MenuItem> listaB=rating(lista,sir[1]);lista=listaB;break;
                    case "Calories": ArrayList<MenuItem> listaC=calories(lista,sir[1]);lista=listaC;break;
                    case "Protein": ArrayList<MenuItem> listaD=protein(lista,sir[1]);lista=listaD;break;
                    case "Fats": ArrayList<MenuItem> listaE=fat(lista,sir[1]);lista=listaE;break;
                    case "Sodium": ArrayList<MenuItem> listaF=sodium(lista,sir[1]);lista=listaF;break;
                    case "Price": ArrayList<MenuItem> listaG=price(lista,sir[1]);lista=listaG;break;
                    default: break;
                }
            }
            return lista;
        }
        public ArrayList<MenuItem> searchP(String cri) {
            try{
                ArrayList<MenuItem> lista=new ArrayList<>();
                Stream<String> stream = Arrays.stream(cri.split( "," ));  //here we have the criteria we are sorting
                ArrayList<String>list = (ArrayList<String>) stream
                        .flatMap(string -> Stream.of(string.split(",")))
                        .collect(toList());
                lista=s.getD().getP();
                lista=cazuri(lista,list);
                seaS();
                return lista;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getProduse().removeAllItems();
            ArrayList<MenuItem> lista= searchP(t.getText());
            for(MenuItem m:lista){
                getProduse().addItem(m.getTitle());
            }

        }
    }
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume= (String) getProduse().getSelectedItem();
            for(MenuItem m:s.getD().getP()) {
                if(m.getTitle().equals(nume)) {
                    getM().add(m);
                    break;
                }
            }
        }
    }
    class ComandaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id=s.getD().getOrders().size();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Order o=new Order(id+1,nume,formatter,date);
            s.getD().getOrders().put(o,m);
            try {
                FileWriter f=new FileWriter(nume,m,date);
                orderS();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            s.writeC();
        }
    }
    class ViewProducts implements ActionListener {
        public void table(JTable table) {
            JFrame frame = new JFrame();
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            /**create a JTable to display the data from an sql table*/
            String[] columnNames = new String[7];
            columnNames[0]="title";
            columnNames[1]="rating";
            columnNames[2]="calories";
            columnNames[3]="protein";
            columnNames[4]="fats";
            columnNames[5]="sodium";
            columnNames[6]="price";
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            for(MenuItem p:s.getD().getP()) {
                String s1=p.getTitle(),s2= String.valueOf(p.getRating()),s3=String.valueOf(p.getCalories()),s4=String.valueOf(p.getProtein()),s5=String.valueOf(p.getFats()),s6=String.valueOf(p.getSodium()),s7=String.valueOf(p.getPrice());
                tableModel.addRow(new Object[]{s1,s2,s3,s4,s5,s6,s7});
            }
            JTable table = new JTable(tableModel);
            table(table);
        }
    }
    class Inapoi implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
