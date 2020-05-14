package ticket4u.sqlConnection;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
 public class DbConnect {

    final String DB_PATH = "jdbc:mysql://localhost:3306/test";
    final String DRIVER = "com.mysql.jdbc.Driver";
    Connection myConn = null;
    //Statement  myStmt = null;
    String userName = null;
    String password = null;


    public DbConnect()
    {
        this.userName = "root";
        this.password = "root";
    }
    public DbConnect(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public Connection connect () throws SQLException,Exception
    {
        Class.forName(DRIVER);
        System.out.print("connecting....");
        myConn = DriverManager.getConnection(DB_PATH, userName , password);
        return myConn;
    }

    public void closeConnection() throws SQLException,Exception
    {
        //myStmt.close();
        myConn.close();
    }
}