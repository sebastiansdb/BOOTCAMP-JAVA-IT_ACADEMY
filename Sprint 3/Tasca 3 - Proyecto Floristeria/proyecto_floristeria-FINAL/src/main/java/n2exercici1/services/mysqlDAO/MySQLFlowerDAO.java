package n2exercici1.services.mysqlDAO;

import n2exercici1.products.Flower;
import n2exercici1.services.productsDAO.FlowerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFlowerDAO implements FlowerDAO {
    final String INSERT = "INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)";
    final String DELETE = "DELETE FROM products WHERE LOWER(name) = LOWER(?)";
    final String GETALL = "SELECT * FROM products WHERE type = \"FLOWER\"";
    final String GETONE = "SELECT * FROM products WHERE type = \"FLOWER\" AND LOWER(name) = LOWER(?)";
    private final Connection connection;

    public MySQLFlowerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Flower flower) {
        try(PreparedStatement insert = connection.prepareStatement(INSERT)) {
            insert.setString(1, flower.getType());
            insert.setString(2, flower.getName());
            insert.setDouble(3, flower.getPrice());
            insert.setString(4, flower.getAttribute());
            if (insert.executeUpdate() == 0){
                System.out.println("This flower could not be stocked");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public void delete(Flower flower) {
        try(PreparedStatement delete = connection.prepareStatement(DELETE)) {
            delete.setString(1, flower.getName().toLowerCase());
            if (delete.executeUpdate() == 0){
                System.out.println("The stock changes could not be made");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Flower> getAll() {
        List <Flower> flowerList = new ArrayList<>();
        try (ResultSet reader = (connection.prepareStatement(GETALL)).executeQuery()) {
            while (reader.next()) {
                flowerList.add(createFlower(reader));
            }
        } catch (SQLException e) {
            flowerList = null;
        }
        return flowerList;
    }
    @Override
    public Flower getOne(String name) {
        Flower flower = null;
        try(PreparedStatement getOne = connection.prepareStatement(GETONE)){
            getOne.setString(1, name.toLowerCase());
            try (ResultSet reader = getOne.executeQuery()) {
                if (reader.next()) {
                    flower = createFlower(reader);
                }
            } catch (SQLException e) {
                flower = null;
            }
        } catch (SQLException e) {
            flower = null;
        }
        return flower;
    }
    private Flower createFlower(ResultSet reader) throws SQLException {
        return new Flower(reader.getString(3), reader.getDouble(4), reader.getString(5));
    }

    @Override
    public void update(Flower flower) {

    }






}
