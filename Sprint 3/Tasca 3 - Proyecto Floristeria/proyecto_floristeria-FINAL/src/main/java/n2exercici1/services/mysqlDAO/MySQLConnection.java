package n2exercici1.services.mysqlDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static n2exercici1.services.InputData.askString;

public class MySQLConnection {

    private String url;
    private String user;
    private String password;
    private static boolean initConnection = false;

    public MySQLConnection (String sqlFileName){
        if (!initConnection) askData(sqlFileName);
    }
    private void askData(String sqlFileName){
        this.url = "jdbc:mysql://localhost:3306/" + sqlFileName;
        this.user = askString("Introduce your username: ");
        this.password = askString("Introduce your password: ");
        initConnection = true;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

}
