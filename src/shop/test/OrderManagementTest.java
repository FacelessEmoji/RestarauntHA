package shop.test;

import shop.adapter.secondary.notification.ConsoleNotificationService;
import shop.adapter.secondary.persistence.InMemoryOrderRepository;
import shop.adapter.secondary.persistence.InMemoryProductRepository;
import shop.domain.model.Order;
import shop.domain.model.OrderStatus;
import shop.domain.port.primary.OrderManagementUseCase;
import shop.domain.port.secondary.NotificationService;
import shop.domain.port.secondary.OrderRepository;
import shop.domain.port.secondary.ProductRepository;
import shop.domain.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderManagementTest {
    public static void main(String[] args) {
        // Инициализация исходящих адаптеров
        OrderRepository orderRepository = new InMemoryOrderRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
        NotificationService notificationService = new ConsoleNotificationService();

        // Инициализация сервиса управления заказами (ядро)
        OrderManagementUseCase orderManagement = new OrderService(
                orderRepository,
                productRepository,
                notificationService
        );

        // Тест: создание заказа
        System.out.println("=== Тест: Создание заказа ===");
        String orderId = orderManagement.createOrder();
        System.out.println("Создан заказ с ID: " + orderId);

        // Тест: добавление товара в заказ
        System.out.println("\n=== Тест: Добавление товара в заказ ===");
        List<?> availableProducts = orderManagement.getAvailableProducts();
        if (!availableProducts.isEmpty()) {
            String productId = ((shop.domain.model.Product) availableProducts.get(0)).getId();
            orderManagement.addProductToOrder(orderId, productId, 2);
            System.out.println("Добавлен товар " + productId + " в заказ " + orderId);
        } else {
            System.out.println("Нет доступных товаров для добавления.");
        }

        // Тест: применение скидки
        System.out.println("\n=== Тест: Применение скидки ===");
        orderManagement.applyDiscount(orderId, 10);
        System.out.println("Применена скидка 10% к заказу " + orderId);

        // Тест: изменение статуса заказа
        System.out.println("\n=== Тест: Изменение статуса заказа ===");
        orderManagement.changeOrderStatus(orderId, OrderStatus.PAID);
        System.out.println("Изменён статус заказа " + orderId + " на PAID");

        // Тест: получение и отображение деталей заказа
        System.out.println("\n=== Тест: Детали заказа ===");
        Optional<Order> orderOpt = orderManagement.getOrder(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            System.out.println(order);
        } else {
            System.out.println("Заказ не найден.");
        }
    }
}
