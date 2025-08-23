package fifth;

// 1. Strategy (Интерфейс Стратегии)
interface DeliveryStrategy {
    double calculateCost(double weight, double distance);
}

// 2. ConcreteStrategy (Конкретные Стратегии)
class StandardDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        // Логика расчета стоимости стандартной доставки
        return weight * 0.5 + distance * 0.1;
    }
}

class CourierDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        // Логика расчета стоимости курьерской доставки
        return weight * 1.0 + distance * 0.2 + 5; // Дополнительная плата за курьера
    }
}

class ExpressDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        // Логика расчета стоимости экспресс-доставки
        return weight * 2.0 + distance * 0.5 + 10; // Самая дорогая доставка
    }
}

// 3. Context (Контекст)
class Order {
    private DeliveryStrategy deliveryStrategy;

    public Order(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public double calculateShippingCost(double weight, double distance) {
        return deliveryStrategy.calculateCost(weight, distance);
    }
}

// Пример использования
public class Strategy {
    public static void main(String[] args) {
        // Создаем заказ и устанавливаем стратегию стандартной доставки
        Order order = new Order(new StandardDelivery());
        double cost = order.calculateShippingCost(2.0, 100.0);
        System.out.println("Стоимость стандартной доставки: " + cost); // Вывод: Стоимость стандартной доставки: 110.0

        // Меняем стратегию на курьерскую доставку
        order.setDeliveryStrategy(new CourierDelivery());
        cost = order.calculateShippingCost(2.0, 100.0);
        System.out.println("Стоимость курьерской доставки: " + cost); // Вывод: Стоимость курьерской доставки: 127.0

        // Меняем стратегию на экспресс-доставку
        order.setDeliveryStrategy(new ExpressDelivery());
        cost = order.calculateShippingCost(2.0, 100.0);
        System.out.println("Стоимость экспресс-доставки: " + cost); // Вывод: Стоимость экспресс-доставки: 154.0
    }
}
