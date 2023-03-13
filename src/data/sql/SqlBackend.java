package data.sql;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class SqlBackend {
    private Connection connection;

    private boolean connected = false;

    public void connect()
    {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/triolingo", "Triolingo", "root8");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("show databases;");
            connected = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void executeQuery(String query)
    {
        try {
            Statement stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
