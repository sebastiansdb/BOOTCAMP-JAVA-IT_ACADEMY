package n1exercici1.connections;

import java.sql.*;

public class MysqlConnection {
    Connection conn;
    PreparedStatement stmt;
    ResultSet res;
    String sql;


    public void connect() {
     try(Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/nombre_bdd", "root", "")){
        String sql = ("INSERT INTO  VALUES ()");
         stmt = conn.prepareStatement(sql);
     }catch (SQLException e){
         e.printStackTrace();
     }
 }
// public void create(){
//     try(Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/nombre_bdd", "root", "")){
//         stmt = conn.prepareStatement(this.sql);
//     }catch (SQLException e){
//         e.printStackTrace();
//     }
// }
// public void update(){
//     try(Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/nombre_bdd", "root", "")){
//         stmt = conn.prepareStatement("");
//     }catch (SQLException e){
//         e.printStackTrace();
//     }
// }
//
//public void delete(){
//    try(Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/nombre_bdd", "root", "")){
//        stmt = conn.prepareStatement("");
//    }catch (SQLException e){
//        e.printStackTrace();
//    }
//}
}
