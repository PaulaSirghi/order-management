package presentationLayer;

import businessLayer.Client;
import businessLayer.MenuItem;
import businessLayer.Order;
import dataLayer.Serializator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class AdminReport extends JFrame{
    private JButton b8 = new JButton("report");
    private JButton b9 = new JButton("inapoi");
    private JComboBox<String> cb = new JComboBox<>();
    private JLabel l1 = new JLabel("start hour");
    private JLabel l2 = new JLabel("end hour");
    private JLabel l3 = new JLabel("number");
    private JLabel l4 = new JLabel("amount");
    private JLabel l5 = new JLabel("day");
    private JTextField t1 = new JTextField();
    private JTextField t2 = new JTextField();
    private JTextField t3 = new JTextField();
    private JTextField t4 = new JTextField();
    private JTextField t5 = new JTextField();
    private JTextArea t6 = new JTextArea();
    private Serializator s;
    public void cbInit()
    {
        cb.addItem("report 1");
        cb.addItem("report 2");
        cb.addItem("report 3");
        cb.addItem("report 4");
    }
    public AdminReport(Serializator s)
    {
        cbInit();
        this.s=s;
        JPanel panou=new JPanel(new GridLayout(8,2));
        panou.add(l1);
        panou.add(t1);
        panou.add(l2);
        panou.add(t2);
        panou.add(l3);
        panou.add(t3);
        panou.add(l4);
        panou.add(t4);
        panou.add(l5);
        panou.add(t5);
        panou.add(cb);
        panou.add(new JLabel());
        panou.add(b8);
        b8.addActionListener(new Rep());
        panou.add(b9);
        b9.addActionListener(new Inapoi());

        JScrollPane sp = new JScrollPane(t6);
        sp.setSize(new Dimension(100,100));
        panou.add(sp);
        this.setContentPane(panou);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600,500);


    }
    public void splitM(String di,Calendar cal1){
        String[] parts1 =di.split(":");
        cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts1[0]));
        cal1.set(Calendar.MINUTE, Integer.parseInt(parts1[1]));
        cal1.set(Calendar.SECOND, Integer.parseInt(parts1[2]));
    }
    public void rep1() throws ParseException {
        ArrayList<MenuItem>l=new ArrayList();
        HashMap<Order, ArrayList<MenuItem>> orders=new HashMap();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String di= (t1.getText());
        String df=(t2.getText());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        splitM(di,cal1);
        splitM(df,cal2);
        s.getD().getOrders().entrySet().stream().filter(f->{String d=f.getKey().getDate().toString();
            String[] parts31 =d.split(" ");
            Calendar cal3 = Calendar.getInstance();
            splitM(parts31[3],cal3);
            if(cal3.after(cal1)) if(cal3.before(cal2)) orders.put(f.getKey(),f.getValue());
         return false;
        }).forEach(System.out::println);
        for(Order o:orders.keySet())
        {
            String s=String.valueOf(o.getClientID())+" "+String.valueOf(o.getOrderID());
            t6.setText(t6.getText()+s+" "+"\n");
        }
    }
    public void rep2() throws ParseException {
        int nr=Integer.parseInt(t3.getText());
        int []sir=new int[s.getD().getP().size()];
        for(int i=0;i<s.getD().getP().size();i++)
            sir[i]=0;
        HashMap<Order, ArrayList<MenuItem>> orders=new HashMap();
        ArrayList<MenuItem> prod=s.getD().getP();
        ArrayList<MenuItem> prod2=new ArrayList<>();
        s.getD().getOrders().entrySet().stream().filter(f->{
            for(MenuItem m:f.getValue())
            {
                int x=prod.indexOf(m);
                sir[x]++;
                if(sir[x]==nr+1)
                    prod2.add(m);
            }
            return false;
        }).forEach(System.out::println);
        for(MenuItem m:prod2)
        {
            t6.setText(t6.getText()+" "+m.getTitle()+"\n");
        }
    }
    public void rep3() throws IllegalStateException {
        int nr=Integer.parseInt(t3.getText());
        int value=Integer.parseInt(t4.getText());
        int []sir=new int[s.getD().getC().size()];
        for(int i=0;i<s.getD().getC().size();i++)
            sir[i]=0;
        ArrayList<Client> cl=s.getD().getC();
        ArrayList<Client> cl2=new ArrayList<>();
        int []pretul=new int[1];
        pretul[0]=0;
        s.getD().getOrders().entrySet().stream().filter(f->{  pretul[0]=0;Client c=new Client();for (Client c1:cl) {
           if(c1.getNume().equals(f.getKey().getClientID()))
           { c=c1;{sir[cl.indexOf(c)]++;if(sir[cl.indexOf(c)]==nr+1) { for(MenuItem m:f.getValue()) { pretul[0]+=m.getPrice(); }if(pretul[0]>value) cl2.add(cl.get(cl.indexOf(c))); }}}}
           return false;
        }).forEach(System.out::println);
        for(Client m:cl2)
        {
            t6.setText(t6.getText()+" "+m.getNume()+"\n");
        }
    }
    public void rep4() throws IllegalStateException {
        String day=t5.getText();
        HashMap<String,Integer>aux=new HashMap<>();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        ArrayList<Order>o=new ArrayList<>();
        s.getD().getOrders().entrySet().stream().filter(f->{
            if(formatter.format(f.getKey().getDate()).equals(day))
                for(MenuItem m:f.getValue()) {
                    int nr=0;
                    if(aux.get(m.getTitle())!=null)
                        nr=aux.get(m.getTitle()).intValue();
                    aux.put(m.getTitle(),nr+1);
            }
            return false;
        }).forEach(System.out::println);
       for(String key:aux.keySet()) {
           String cheie=key;
           String val=aux.get(key).toString();
           t6.setText(t6.getText()+"produsul "+cheie+" cu frectventa: "+val+"\n");
       }
    }
    public void afis(){
        int ct=0;
        System.out.println("COMENZILE PLASATE SUNT:");
        for(Order o:s.getD().getOrders().keySet()){
            ct++;
            int ct2=0;
            System.out.println("comanda plasata de "+o.getClientID());
            for(ArrayList<MenuItem> arr:s.getD().getOrders().values()){
                ct2++;
                if(ct2==ct)
                {
                    for(MenuItem i:arr){
                        System.out.println(i.getTitle()+" "+i.getPrice());
                    }
                }
            }
            System.out.println();
        }
    }
    class Rep implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sir=cb.getSelectedItem().toString();
            t6.setText("");
            System.out.println();
            afis();
            switch (sir) {
                case "report 1":
                    try {
                        rep1();
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    break;
                case "report 2":
                    try {
                        rep2();
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    break;
                case "report 3":
                    rep3();break;
                case "report 4":
                    rep4();break;
                default:
                    break;
            }
        }
    }
    class Inapoi implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
