package n2exercici1.services.mysqlDAO;

import n2exercici1.sales.Sale;
import n2exercici1.services.productsDAO.SaleDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySQLSaleDAO implements SaleDAO {
    final String INSERT = "INSERT INTO sales (totalPrice, date, productList) VALUES (?, ?, ?)";
    final String GETALL = "SELECT * FROM sales";
    final String GETONE = "SELECT * FROM sales WHERE id = ?";
    private final Connection connection;

    public MySQLSaleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Sale sale) {
        try(PreparedStatement insert = connection.prepareStatement(INSERT)) {
            insert.setDouble(1, sale.getSaleAmount());
            insert.setDate(2, new java.sql.Date(sale.getSaleDate().getTime()));
            insert.setString(3, String.join(";", sale.getProductList()));
            if (insert.executeUpdate() == 0){
                System.out.println("This sale could not be registered");
            }
        } catch (SQLException e) {
            System.out.println("The sales changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Sale> getAll() {
        List <Sale> saleList = new ArrayList<>();
        try (ResultSet reader = (connection.prepareStatement(GETALL)).executeQuery()) {
            while (reader.next()) {
                saleList.add(createSale(reader));
            }
        } catch (SQLException e) {
            saleList = null;
        }
        return saleList;
    }
    @Override
    public Sale getOne (Integer id) {
        Sale sale = null;
        try(PreparedStatement getOne = connection.prepareStatement(GETONE)){
            getOne.setInt(1, id);
            try (ResultSet reader = getOne.executeQuery()) {
                if (reader.next()) {
                    sale = createSale(reader);
                }
            } catch (SQLException e){
                sale = null;
            }
        } catch (SQLException e) {
            sale = null;
        }
        return sale;
    }
    private Sale createSale(ResultSet reader) throws SQLException {
        return new Sale(reader.getDouble(2),
                reader.getDate(3),
                Arrays.asList((reader.getString(4)).split(";")));
    }

    @Override
    public void delete(Sale sale) {}
    @Override
    public void update(Sale sale) {

    }






}
