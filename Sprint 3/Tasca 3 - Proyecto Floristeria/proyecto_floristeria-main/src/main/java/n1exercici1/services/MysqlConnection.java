package n1exercici1.services;

import java.sql.*;

public class MysqlConnection {
    Connection conn = null;
    Statement stmt = null;
    ResultSet res = null;
 public void connect() {
     try(Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/nombre_bdd", "root", "")){
      stmt = conn.createStatement();
      res = stmt.executeQuery("SHOW DATABASE");
      while (res.next()){
          System.out.println(res.getString("name"));
      }
     }catch (SQLException e){
         e.printStackTrace();
     }
 }

}
