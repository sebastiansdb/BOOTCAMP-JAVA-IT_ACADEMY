package n1exercici1.services.mysqlDAO;

import n1exercici1.connections.MysqlConnection;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.services.productsDAO.FlowerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFlowerDAO implements FlowerDAO {
    final String INSERT = "INSERT INTO Tabla (col1, clo2) VALUES(?,?)";
    final String UPDATE = "UPDATE Tabla SET nombre = ? WHERE id = ?";
    final String DELETE = "DELETE FROM Tabla WHERE ID = ?";
    final String GETALL = "SELECT* FROM flower";
    final String GETONE = " SELECT flower.id FROM flower";

    private Connection conn;

    public MySQLFlowerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Flower item) {
    try(PreparedStatement stmt = conn.prepareStatement(INSERT)) {
    stmt.setInt(1, item.getIdProduct());
    stmt.setString(2, item.getName());
    stmt.setString(3, item.getColor());
    stmt.setDouble(4, item.getPrice());
    if (stmt.executeUpdate() == 0){
        System.out.println(" was not inserted");
    }
    try(ResultSet res = stmt.getGeneratedKeys()){
        if (res.next()) {
            item.setIdProduct(res.getInt(1));
        }else {
            System.out.println("there is no id column");
        }
    }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void delete(Flower item) {
    try(PreparedStatement stmt = conn.prepareStatement(DELETE)) {
        stmt.setInt(1, item.getIdProduct());
        if (stmt.executeUpdate() == 0){
            System.out.println(" was not deleted");
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void update(Flower item) {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Flower convertir(ResultSet res) throws SQLException {
        String name = res.getString("name");
        String color = res.getString("color");
        double price = res.getDouble("price");
        Product flower = new Flower(name, price, color);
        // en caso de id hacer un setId(res.getInt("id"));
        return (Flower) flower;
    }

        @Override
    public List<Flower> getAll() {
           List <Flower> flower = new ArrayList<>();
            try(PreparedStatement stmt = conn.prepareStatement(GETALL)) {
                try (ResultSet res =  stmt.executeQuery()) {
                    while (res.next()) {
                        flower.add(convertir(res));

                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return flower;
    }

    @Override
    public Flower getOne(Integer id) {
        Flower flower = null;
        try(PreparedStatement stmt = conn.prepareStatement(GETONE)) {
            stmt.setInt(1, id);
            try (ResultSet res =  stmt.executeQuery()) {
                if (res.next()) {
                    flower = convertir(res);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flower;
    }
}
