package presentationLayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame implements Observer{
    private JButton b=new JButton("OK");
    private JTextField t=new JTextField("");
    public EmployeeView() {
        this.setTitle("Employee");
        JPanel panou=new JPanel();
        panou.add(t);
        panou.add(b);
        b.addActionListener(new ButonL());
        this.setContentPane(panou);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(200,100);
    }

    @Override
    public void update(String nume) {
          t.setText(nume);
    }

    class ButonL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
