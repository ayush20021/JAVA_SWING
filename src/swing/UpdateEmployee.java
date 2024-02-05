package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class UpdateEmployee  extends JFrame {

    JLabel ID,Name,Salary,Depertament,note,msg,Address;
    JTextField Idinp,Nameinp,SalaryInp,depInp,Addinp;

    JButton Update,Search,Home;

    String IDI;

    public ArrayList<String> Search(String id) throws ClassNotFoundException, SQLException {
        ArrayList<String> EmpDetails = new ArrayList<>();
        String jdbcUrl = "jdbc:mysql://localhost:3306/user";
        String username = "root";
        String password = "";
        String sql = "SELECT * FROM `users` WHERE `ID` = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet  = preparedStatement.executeQuery();

        while (resultSet.next()){
            String Name = resultSet.getString("Name");
            String Sal = resultSet.getString("SAL");
            String  Address = resultSet.getString("Address");

            EmpDetails.add(Name);
            EmpDetails.add(Sal);
            EmpDetails.add(Address);

            return EmpDetails;

        }
        return  EmpDetails;

    }
    public  boolean UpdateEmployee(String id,String name,String sal,String add) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/user";
        String username = "root";
        String password = "";
        String sql = "UPDATE `users` SET `Name` = ?, `SAL` = ?, `Address` = ? WHERE `ID` = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,sal);
        preparedStatement.setString(3,add);
        preparedStatement.setString(4,id);
        int ab = preparedStatement.executeUpdate();
        return ab > 0;



    }

    UpdateEmployee(){
        setLayout(null);
        ID = new JLabel("Enter the ID");
        note = new JLabel("Enter the ID to get Details :");
        Name = new JLabel("Name :");
        Salary = new JLabel("Salary :");
        Depertament = new JLabel("Department :");
        Idinp = new JTextField(10);
        Nameinp = new JTextField(20);
        SalaryInp = new JTextField(10);
        depInp = new JTextField(10);
        msg = new JLabel();
        Address = new JLabel("Address");
        Addinp = new JTextField(10);
        Search = new JButton("Search");

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String id= Idinp.getText();


                try {
                    ArrayList<String> EmpData = Search(id);
                    if(EmpData.isEmpty()){
                        msg.setText("NO Data Found");
                        msg.setForeground(Color.RED);
                        Nameinp.setText("");
                        SalaryInp.setText("");
                        Addinp.setText("");
                        return;
                    }
                    IDI=id;
                    Nameinp.setText(EmpData.getFirst());
                    SalaryInp.setText(EmpData.get(1));
                    Addinp.setText(EmpData.get(2));
                    msg.setText("Data Found");
                    msg.setForeground(Color.GREEN);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        Update = new JButton("UPDATE");
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name= Nameinp.getText();
                 String salary = SalaryInp.getText();
                 String  address = Addinp.getText();
                try {
                    boolean Status = UpdateEmployee(IDI,Name,salary,address);
                    msg.setText("Data Successfully Updated");
                    msg.setForeground(Color.GREEN);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        Home= new JButton("Home");
        Home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
            }
        });

        ID.setBounds(50,50,200,20);
        Idinp.setBounds(160,50,50,20);
        Search.setBounds(240,50,90,20);
        note.setBounds(50,80,200,20);
        Name.setBounds(50,120,50,20);
        Nameinp.setBounds(130,120,90,20);
        Salary.setBounds(50,150,50,20);
        SalaryInp.setBounds(130,150,90,20);
//        Depertament.setBounds(50,180,80,20);
//        depInp.setBounds(130,180,90,20);
        Address.setBounds(50,180,80,20);
        Addinp.setBounds(130,180,90,20);
        Update.setBounds(50,210,120,30);
        Home.setBounds(200,210,110,30);
        msg.setBounds(50,250,170,20);


        add(ID);
        add(Idinp);
        add(Search);
        add(note);
        add(Name);
        add(Nameinp);
        add(Salary);
        add(SalaryInp);
//        add(Depertament);
//        add(depInp);
        add(Address);
        add(Addinp);
        add(Update);
        add(Home);
        add(msg);

        setSize(400,400);
        setVisible(true);

    }

    public static void main(String[] args) {
        new UpdateEmployee();
    }



}
