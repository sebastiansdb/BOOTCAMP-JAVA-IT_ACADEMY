package n1exercici1.services.mysqlDAO;

import n1exercici1.services.productsDAO.DAOManager;
import n1exercici1.services.productsDAO.DecorationDAO;
import n1exercici1.services.productsDAO.FlowerDAO;
import n1exercici1.services.productsDAO.TreeDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager implements DAOManager {
    private Connection conn;
    private DecorationDAO decoration;
    private FlowerDAO flower;
    private TreeDAO tree;

    public MySQLManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/nombre_bdd", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DecorationDAO getDecorationDAO() {
        return null;
    }

    @Override
    public FlowerDAO getFlowerDAO() {
        if (flower == null){
            flower = new MySQLFlowerDAO(conn);
        }
        return flower;
    }

    @Override
    public TreeDAO getTreeDAO() {
        return null;
    }
}
