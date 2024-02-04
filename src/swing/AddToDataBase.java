package swing;

import java.sql.*;

public class AddToDataBase {

static  boolean Add(String Name,String Id,String Sal,String Add) throws ClassNotFoundException, SQLException {
    boolean status = false;
    try {
        String jdbcUrl = "jdbc:mysql://localhost:3306/user";
        String username = "root";
        String password = "";
        String sql = "INSERT INTO `users` (`SR.ID`, `Name`, `ID`, `SAL`, `Address`) VALUES (NULL, ?, ?, ?, ?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,Name);
        preparedStatement.setString(2,Id);
        preparedStatement.setString(3,Sal);
        preparedStatement.setString(4,Add);

        int set = preparedStatement.executeUpdate();

        return  true;
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }



}



}
