package n3exercici1.services.productsDAO;

import java.util.List;

public interface DAO<T, K> {
    void insert(T item);
    void delete(T item);
    void update(T item);
    List<T> getAll();
    int getLastID();
    T getOne(K id);
}
