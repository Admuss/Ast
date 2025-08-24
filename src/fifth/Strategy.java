package fifth;

interface DeliveryStrategy {
    double calculateCost(double weight, double distance);
}

class StandardDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, double distance) {

        return weight * 0.5 + distance * 0.1;
    }
}

class CourierDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, double distance) {

        return weight * 1.0 + distance * 0.2 + 5; 
    }
}

class ExpressDelivery implements DeliveryStrategy {
    @Override
    public double calculateCost(double weight, double distance) {

        return weight * 2.0 + distance * 0.5 + 10; 
    }
}


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


public class Strategy {
    public static void main(String[] args) {
        // Создаем заказ и устанавливаем стратегию стандартной доставки
        Order order = new Order(new StandardDelivery());
        double cost = order.calculateShippingCost(2.0, 100.0);
        System.out.println("Стоимость стандартной доставки: " + cost); 

        // Меняем стратегию на курьерскую доставку
        order.setDeliveryStrategy(new CourierDelivery());
        cost = order.calculateShippingCost(2.0, 100.0);
        System.out.println("Стоимость курьерской доставки: " + cost); 

        // Меняем стратегию на экспресс-доставку
        order.setDeliveryStrategy(new ExpressDelivery());
        cost = order.calculateShippingCost(2.0, 100.0);
        System.out.println("Стоимость экспресс-доставки: " + cost); 
    }
}
