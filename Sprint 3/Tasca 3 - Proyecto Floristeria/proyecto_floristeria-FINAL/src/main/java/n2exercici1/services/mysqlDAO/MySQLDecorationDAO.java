package n2exercici1.services.mysqlDAO;

import n2exercici1.products.Decoration;
import n2exercici1.products.enums.MadeOf;
import n2exercici1.services.productsDAO.DecorationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDecorationDAO implements DecorationDAO {

    final String INSERT = "INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)";
    final String DELETE = "DELETE FROM products WHERE LOWER(name) = LOWER(?)";
    final String GETALL = "SELECT * FROM products WHERE type = \"DECORATION\"";
    final String GETONE = "SELECT * FROM products WHERE type = \"DECORATION\" AND LOWER(name) = LOWER(?)";
    private final Connection connection;

    public MySQLDecorationDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Decoration decoration) {
        try(PreparedStatement insert = connection.prepareStatement(INSERT)) {
            insert.setString(1, decoration.getType());
            insert.setString(2, decoration.getName());
            insert.setDouble(3, decoration.getPrice());
            insert.setString(4, decoration.getAttribute());
            if (insert.executeUpdate() == 0){
                System.out.println("This decoration could not be stocked");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public void delete(Decoration decoration) {
        try(PreparedStatement delete = connection.prepareStatement(DELETE)) {
            delete.setString(1, decoration.getName().toLowerCase());
            if (delete.executeUpdate() == 0){
                System.out.println("The stock changes could not be made");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Decoration> getAll() {
        List <Decoration> decorationList = new ArrayList<>();
        try (ResultSet reader = (connection.prepareStatement(GETALL)).executeQuery()) {
            while (reader.next()) {
                decorationList.add(createDecoration(reader));
            }
        } catch (SQLException e) {
            decorationList = null;
        }
        return decorationList;
    }
    @Override
    public Decoration getOne(String name) {
        Decoration decoration = null;
        try(PreparedStatement getOne = connection.prepareStatement(GETONE)){
            getOne.setString(1, name.toLowerCase());
            try (ResultSet reader = getOne.executeQuery()) {
                if (reader.next()) {
                    decoration = createDecoration(reader);
                }
            } catch (SQLException e){
                decoration = null;
            }
        } catch (SQLException e) {
            decoration = null;
        }
        return decoration;
    }
    private Decoration createDecoration(ResultSet reader) throws SQLException {
        return new Decoration(reader.getString(3),
                reader.getDouble(4),
                reader.getString(5).equalsIgnoreCase("WOOD") ? MadeOf.WOOD : MadeOf.PLASTIC);
    }

    @Override
    public void update(Decoration decoration) {

    }
}
