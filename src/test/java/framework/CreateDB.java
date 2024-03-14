package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CreateDB {

    public Statement dbConnection() throws Exception {
        {
            String dbUrl = "jdbc:mysql://localhost:3306/testdb";
            String userName = "";
            String password = "";

            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            Statement statement = connection.createStatement();
            return statement;
        }

    }
}
