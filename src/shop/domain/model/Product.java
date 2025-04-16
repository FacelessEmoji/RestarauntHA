// Файл: src/shop/domain/model/Product.java
package shop.domain.model;

public class Product {
    private String id;      // геттер для id
    private String name;    // геттер для name
    private double price;   // геттер для price

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (" + price + " руб.)";
    }
}
