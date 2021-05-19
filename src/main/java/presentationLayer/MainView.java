package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainView extends JFrame {
    JButton b1=new JButton("login");
    JButton b2=new JButton("register");
    JLabel nume=new JLabel("username");
    JLabel parola=new JLabel("password");
    JLabel rol=new JLabel("user type");
    JTextField t1=new JTextField();
    JTextField t2=new JTextField();
    JTextField t3=new JTextField();
    public MainView()
    {
        this.setTitle("Logare");
        t1.setColumns(10);
        t2.setColumns(10);
        t3.setColumns(10);
        JPanel sus=new JPanel();
        JPanel p11=new JPanel();
        JPanel p12=new JPanel();
        JPanel p13=new JPanel();
        p11.add(nume);
        p11.add(t1);
        p12.add(parola);
        p12.add(t2);
        p13.add(rol);
        p13.add(t3);
        JPanel p2=new JPanel();
        p2.add(b1);
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2.add(b2);
        JPanel cont=new JPanel();
        cont.setLayout(new GridLayout(5,1));
        cont.add(sus);
        cont.add(p11);
        cont.add(p12);
        cont.add(p13);
        cont.add(p2);
        this.setContentPane(cont);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(300,300);
    }

    public JTextField getT1() {
        return t1;
    }

    public JTextField getT2() {
        return t2;
    }

    public JTextField getT3() {
        return t3;
    }

    public void login(ActionListener mal) {
        b1.addActionListener(mal);
    }
    public void register(ActionListener mal){
        b2.addActionListener(mal);
    }
}
