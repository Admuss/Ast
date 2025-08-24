package fifth;

interface Coffee {
    String getDescription();
    double getCost();
}

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

public class Decorator {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Описание: " + coffee.getDescription()); 
        System.out.println("Стоимость: " + coffee.getCost()); 

        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println("Описание: " + milkCoffee.getDescription()); 
        System.out.println("Стоимость: " + milkCoffee.getCost()); 

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Описание: " + sugarMilkCoffee.getDescription()); 
        System.out.println("Стоимость: " + sugarMilkCoffee.getCost()); 

        Coffee cinnamonSugarMilkCoffee = new CinnamonDecorator(sugarMilkCoffee);
        System.out.println("Описание: " + cinnamonSugarMilkCoffee.getDescription()); 
        System.out.println("Стоимость: " + cinnamonSugarMilkCoffee.getCost()); 
    }
}
