Структура:
```text
└── src/
└── shop/
├── adapter/
│      ├── primary/
│      │       └── console/
│      │               └── ConsoleOrderManager.java
│      └── secondary/
│              ├── notification/
│              │         └── ConsoleNotificationService.java
│              └── persistence/
│                        ├── InMemoryOrderRepository.java
│                        └── InMemoryProductRepository.java
├── domain/
│      ├── model/
│      │       ├── Order.java
│      │       ├── OrderItem.java
│      │       ├── OrderStatus.java
│      │       └── Product.java
│      ├── port/
│      │       ├── primary/
│      │       │         └── OrderManagementUseCase.java
│      │       └── secondary/
│      │                 ├── NotificationService.java
│      │                 ├── OrderRepository.java
│      │                 └── ProductRepository.java
│      └── service/
│              └── OrderService.java
├── ShopApplication.java
└── test/
└── OrderManagementTest.java
```


Основные компоненты
Домен (ядро) – модель и бизнес-логика:
В пакете domain/model находятся сущности заказа, товара, а также перечисление статусов. Класс OrderService в пакете domain/service реализует интерфейс входящего порта (OrderManagementUseCase) и содержит бизнес-логику по созданию заказа, добавлению товара, изменению статуса и пр.

Порты:

Входной порт (primary): интерфейс OrderManagementUseCase, который определяет функции создания заказа, добавления товара, применения скидки, смены статуса, получения заказа и списка доступных товаров.

Исходящие порты (secondary): интерфейсы OrderRepository, ProductRepository и NotificationService для работы с хранилищем и уведомлениями.

Адаптеры:

Входящие адаптеры: консольный интерфейс (ConsoleOrderManager) для управления заказами через терминал.

Исходящие адаптеры: реализации in-memory репозиториев (InMemoryOrderRepository, InMemoryProductRepository) и имитация уведомлений через консоль (ConsoleNotificationService).

ShopApplication:
Точка входа, где создаются адаптеры, внедряются зависимости в OrderService и запускается входящий адаптер (консольное меню).