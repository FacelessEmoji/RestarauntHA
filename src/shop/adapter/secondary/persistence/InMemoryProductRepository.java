package shop.adapter.secondary.persistence;

import shop.domain.model.Product;
import shop.domain.port.secondary.ProductRepository;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public InMemoryProductRepository() {
        // Инициализация тестовыми продуктами для ресторана
        addProduct(new Product("P1", "Куриное филе", 450.0));
        addProduct(new Product("P2", "Картофель", 30.0));
        addProduct(new Product("P3", "Майонез", 80.0));
        addProduct(new Product("P4", "Булочка для бургера", 25.0));
        addProduct(new Product("P5", "Говяжья котлета", 120.0));
        addProduct(new Product("P6", "Салат Айсберг", 50.0));
        addProduct(new Product("P7", "Сыр Чеддер", 90.0));
        addProduct(new Product("P8", "Кетчуп", 60.0));
    }

    private void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
