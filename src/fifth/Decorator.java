package fifth;

// 1. Component (Компонент)
interface Coffee {
    String getDescription();
    double getCost();
}

// 2. ConcreteComponent (Конкретный Компонент)
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Простой кофе";
    }

    @Override
    public double getCost() {
        return 1.0;
    }
}

// 3. Decorator (Декоратор)
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// 4. ConcreteDecorator (Конкретные Декораторы)
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", с молоком";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", с сахаром";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}

class CinnamonDecorator extends CoffeeDecorator {
    public CinnamonDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", с корицей";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.3;
    }
}

// 5. Client (Клиент)
public class Decorator {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Описание: " + coffee.getDescription()); // Вывод: Описание: Простой кофе
        System.out.println("Стоимость: " + coffee.getCost()); // Вывод: Стоимость: 1.0

        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("Описание: " + milkCoffee.getDescription()); // Вывод: Описание: Простой кофе, с молоком
        System.out.println("Стоимость: " + milkCoffee.getCost()); // Вывод: Стоимость: 1.5

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Описание: " + sugarMilkCoffee.getDescription()); // Вывод: Описание: Простой кофе, с молоком, с сахаром
        System.out.println("Стоимость: " + sugarMilkCoffee.getCost()); // Вывод: Стоимость: 1.7

        Coffee cinnamonSugarMilkCoffee = new CinnamonDecorator(sugarMilkCoffee);
        System.out.println("Описание: " + cinnamonSugarMilkCoffee.getDescription()); // Вывод: Описание: Простой кофе, с молоком, с сахаром, с корицей
        System.out.println("Стоимость: " + cinnamonSugarMilkCoffee.getCost()); // Вывод: Стоимость: 2.0
    }
}
