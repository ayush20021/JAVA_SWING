package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DellEmployee extends JFrame {


    JLabel ID,Name,Sal,Address,Note,msg;
    JButton Search,Delete;

    JTextArea Idinp;

    public  boolean DeleteData(String id) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/user";
        String username = "root";
        String password = "";
        String sql = "DELETE FROM `users` WHERE `ID` = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int ab = preparedStatement.executeUpdate();
        return ab > 0;
    }

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
        ResultSet  resultSet  = preparedStatement.executeQuery();

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


    DellEmployee(){
        setLayout(null);
        ID = new JLabel("Enter ID");
        Idinp = new JTextArea();
        Name = new JLabel();
        Sal = new JLabel();
        Address = new JLabel();
        Note = new JLabel("Employee Details will be shone below");
        Search = new JButton("Search");
        msg = new JLabel();

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = Idinp.getText();

                System.out.println();

                try {
                    ArrayList<String> EmpDetails = Search(ID);

//                    System.out.println(EmpDetails.size());

                    if(EmpDetails.isEmpty()){
                        msg.setText("NO EMPLOYEE FOUND ");
                        msg.setForeground(Color.RED);
                        Name.setText("");
                        Sal.setText("");
                        Address.setText("");
                        return;
                    }
                    msg.setText("EMPLOYEE FOUND");
                    msg.setForeground(Color.GREEN);
                    String name = EmpDetails.getFirst();
                    String sal = EmpDetails.get(1);
                    String Add = EmpDetails.get(2);
                    // Assigning Value
                    Name.setText("Name: "+name);
                    Sal.setText("Salary: "+sal);
                    Address.setText("Address: "+Add);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });



        Delete = new JButton("Delete");
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Idinp.getText();

                try {
                    boolean status = DeleteData(id);
                    if(status){
                        msg.setText("EMPLOYEE DESTROYED :)");
                        msg.setForeground(Color.RED);
                        System.out.println("Employee Deleted");
                    }
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });



        ID.setBounds(50,50,60,30);
        Idinp.setBounds(120,50,60,20);
        Note.setBounds(50,100,300,20);
        Name.setBounds(50,130,100,20);
        Sal.setBounds(50,160,80,20);
        Address.setBounds(50,190,150,20);
        Search.setBounds(50,240,100,30);
        Delete.setBounds(200,240,100,30);
        msg.setBounds(50,280,180,60);




        add(ID);
        add(Idinp);
        add(Note);
        add(Name);
        add(Sal);
        add(Address);
        add(Search);
        add(Delete);
        add(msg);
        setSize(400,400);
        setVisible(true);

    }

    public static void main(String[] args) {
        new DellEmployee();
    }






}
