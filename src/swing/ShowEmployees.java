package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ShowEmployees extends JFrame {
    JTable table;
    JButton btn;

    public ArrayList<Employee> GetData() throws ClassNotFoundException, SQLException {
        ArrayList<Employee> EmpData = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost:3306/user";
        String username = "root";
        String password = "";
        String sql = "SELECT  * from  users";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(sql);

        while (set.next()){
            String Name = set.getString("Name");
            String Sal = set.getString("SAL");
            String Address = set.getString("Address");
            EmpData.add(new Employee(Name,Sal,Address));


        }
        return EmpData;


    }



    ShowEmployees() throws SQLException, ClassNotFoundException {
        // Use BorderLayout for the frame
        setLayout(null);
        ArrayList<Employee> EMP= GetData();

        String data[][] =new
                String[EMP.size()][3];

        for (int i = 0; i<EMP.size();i++){
            Employee emp = EMP.get(i);
            String Name = emp.getName();
            String Sal = emp.getSal();
            String Address = emp.getAddress();
            data[i] = new String[]{Name,Sal,Address};
        }

        String column[] = {"Name", "Salary", "Address"};

        btn = new JButton("Home");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
            }
        });


        DefaultTableModel model = new DefaultTableModel(data, column);
        table = new JTable(model);
        table.setBounds(10, 10, 380, 300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10,10,380,300);
        btn.setBounds(50,350,100,30);



        add(scrollPane);
        add(btn);

        // Set frame size and visibility

        setSize(420, 500);
        setVisible(true);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       new ShowEmployees();
    }
}
