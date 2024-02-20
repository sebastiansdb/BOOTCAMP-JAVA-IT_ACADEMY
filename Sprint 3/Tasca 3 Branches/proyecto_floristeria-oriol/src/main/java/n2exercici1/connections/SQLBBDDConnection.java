package n2exercici1.connections;

import static n2exercici1.services.InputData.askString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLBBDDConnection {

    private static String url;
    private static String user;
    private static String password;
    private static boolean initConnection = false;

    private static void checkDataConnection(String sqlFileName){
        if (!initConnection) askData(sqlFileName);
    }
    private static void askData(String sqlFileName){
        url = "jdbc:mysql://localhost:3306/"+sqlFileName;
        user = "root";//askString("Introduce your username: ");
        password = "rootroot";//askString("Introduce your password: ");
        initConnection = true;
    }

    public static Connection getConnection(String sqlFileName) throws SQLException {
        checkDataConnection(sqlFileName);
        return DriverManager.getConnection(url, user, password);
    }

}
