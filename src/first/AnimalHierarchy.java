package first;
interface HasBackbone {
    boolean hasBackbone(); // наличие позвоночника
}
interface HasFur {
    String getFurType(); // описание меха
}
interface LivesInWater {
    String getHabitat(); // в каком водоёме живёт
}

abstract class Animal {
   
    public abstract String getType(); 
    @Override
    public String toString() {
        return getType();
    }
}

abstract class Mammal extends Animal implements HasBackbone{
    public Mammal() {
    }

    @Override
    public boolean hasBackbone() {
        return true;
    }
    @Override
    public String toString() {
        return super.toString() + ", Has Backbone: " + hasBackbone();
    }
    @Override
    public String getType() {
        return "Mammal";
    }
}


class Fish extends Animal implements LivesInWater {
    private String habitat;

    public Fish(String habitat) {
        this.habitat = habitat;
    }

    @Override
    public String getType() {
        return "Fish";
    }
    @Override
    public String getHabitat() {
        return this.habitat;
    }
    @Override
    public String toString() {
        return super.toString() + ", Habitat: " + getHabitat();
    }
}

class Cat extends Mammal implements HasFur {
    private String furType;

    public Cat(String furType) {
        this.furType = furType;
    }

    @Override
    public String getType() {
        return "Cat";
    }
    @Override
    public String getFurType() {
        return this.furType;
    }
    @Override
    public String toString() {
        return super.toString() + ", Fur Type: " + getFurType();
    }
}

class Bear extends Mammal implements HasFur {
    private String furType;

    public Bear(String furType) {
        this.furType = furType;
    }

    @Override
    public String getType() {
        return "Bear";
    }
    @Override
    public String getFurType() {
        return this.furType;
    }
    @Override
    public String toString() {
        return super.toString() + ", Fur Type: " + getFurType();
    }
}

class Whale extends Mammal implements LivesInWater {
    private String habitat;

    public Whale(String habitat) {
        this.habitat = habitat;
    }

    @Override
    public String getType() {
        return "Whale";
    }
    @Override
    public String getHabitat() {
        return this.habitat;
    }
    @Override
    public String toString() {
        return super.toString() + ", Habitat: " + getHabitat();
    }
}

public class AnimalHierarchy {
    public static void main(String[] args) {
        Cat cat = new Cat("Long");
        Bear bear = new Bear("Thick");
        Whale whale = new Whale("Ocean");
        Fish fish = new Fish("River");

        System.out.println(cat);  
        System.out.println(bear); 
        System.out.println(whale);
        System.out.println(fish);   

    }
}