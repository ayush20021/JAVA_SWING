package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends JFrame {

    JButton Addemp,Delemp;

    public  Home(){
        setLayout(null);
        Addemp = new JButton("ADD EMP");

        Addemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployee();
                dispose();
            }
        });


        Delemp = new JButton("DEL EMP");

        Delemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DellEmployee();
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });


        Addemp.setBounds(40,30,100,30);
        Delemp.setBounds(40,100,100,30);
        add(Addemp);
        add((Delemp));
        setSize(200,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Home();
    }
}
