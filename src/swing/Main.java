package swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Main extends JFrame {

    JMenuBar menu;
    JMenu Employee, EmployeeDetails,Account,Utilitites;

    JMenuItem Add,Update,Delete,Show_Employees;


    Main(){
        setLayout(null);
        menu = new JMenuBar();
        Employee = new JMenu("Employee");
        EmployeeDetails = new JMenu("EmployeeDetails");
        Show_Employees = new JMenuItem("Show Employees");
        Show_Employees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ShowEmployees();
                    dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        EmployeeDetails.add(Show_Employees);

        Account = new JMenu("Account");
        Utilitites = new JMenu("Utilities");
        Add = new JMenuItem("Add");
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployee();
                dispose();
            }
        });
        Update = new JMenuItem("Update");
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmployee();
                dispose();
            }
        });

        Delete = new JMenuItem("Delete");
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DellEmployee();
                dispose();
            }
        });
        menu.add(Employee);
        menu.add(EmployeeDetails);
        menu.add(Account);
        menu.add(Utilitites);
        Employee.add(Add);
        Employee.add(Update);
        Employee.add(Delete);

        menu.setBounds(70,0,300,30);
        add(menu);
        setSize(500,500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }


}
