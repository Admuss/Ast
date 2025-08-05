package first;
interface HasWheels {
    int getNumberOfWheels();//число колёс
}

interface HasWings {
    double getWingspan();//длина крыльев
}

interface HasPropeller {
    int getNumberOfPropellers();//число лопастей
}

interface Cargo {
    double getCargoCapacity();//максимальный переносимый вес
}

abstract class vehicle{
    public abstract String getType();
    @Override
    public String toString() {
        return getType();
    }
}


class Taxi extends vehicle implements HasWheels {
    private int numberOfWheels;

    public Taxi( int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public String getType() {
        return "Taxi";
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public String toString() {
        return super.toString() + ", Wheels: " + numberOfWheels;
    }


}
class Truck extends vehicle implements HasWheels, Cargo {

    private int numberOfWheels;
    private double cargoCapacity;

    public Truck(int numberOfWheels, double cargoCapacity) {
        this.numberOfWheels = numberOfWheels;
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String getType() {
        return "Truck";
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public String toString() {
        return super.toString() + ", Wheels: " + numberOfWheels + ", Cargo Capacity: " + cargoCapacity;
    }
}
class Tanker extends vehicle implements HasPropeller, Cargo{
    private double cargoCapacity;
    private int numberOfPropellers;

    public Tanker(double cargoCapacity, int numberOfPropellers) {
        this.numberOfPropellers = numberOfPropellers;
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String getType() {
        return "Tanker";
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }
    @Override
    public int getNumberOfPropellers() {
        return numberOfPropellers;
    }
    @Override
    public String toString() {
        return super.toString() + ", Cargo Capacity: " + cargoCapacity;
    }
}
class Boat extends vehicle implements HasPropeller, Cargo {
    private double cargoCapacity;
    private int numberOfPropellers;

    public Boat(double cargoCapacity, int numberOfPropellers) {

        this.numberOfPropellers = numberOfPropellers;
    }

    @Override
    public String getType() {
        return "Boat";
    }
    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }
    @Override
    public int getNumberOfPropellers() {
        return numberOfPropellers;
    }

    @Override
    public String toString() {
        return super.toString() + ", Propellers: " + numberOfPropellers;
    }
}

class Helicopter extends vehicle implements HasWheels, HasPropeller, Cargo {
    private int numberOfWheels;
    private int numberOfPropellers;
    private double cargoCapacity;

    public Helicopter(int numberOfWheels, int numberOfPropellers, double cargoCapacity) {
        this.numberOfWheels = numberOfWheels;
        this.numberOfPropellers = numberOfPropellers;
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String getType() {
        return "Helicopter";
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public int getNumberOfPropellers() {
        return numberOfPropellers;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public String toString() {
        return super.toString() + ", Wheels: " + numberOfWheels + ", Propellers: " + numberOfPropellers + ", Cargo Capacity: " + cargoCapacity;
    }
}
class Airplane extends vehicle implements HasWheels, HasPropeller, HasWings, Cargo {
    private int numberOfWheels;
    private double wingspan;
    private double cargoCapacity;
    private int numberOfPropellers;

    public Airplane( int numberOfWheels, double wingspan, double cargoCapacity, int numberOfPropellers) {
        this.numberOfWheels = numberOfWheels;
        this.wingspan = wingspan;
        this.cargoCapacity = cargoCapacity;
        this.numberOfPropellers = numberOfPropellers;
    }

    @Override
    public String getType() {
        return "Airplane";
    }
    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }
    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }
    @Override
    public int getNumberOfPropellers() {
        return numberOfPropellers;
    }
    @Override
    public double getWingspan() {
        return wingspan;
    }
    @Override
    public String toString() {
        return super.toString() + ", Wheels: " + numberOfWheels + ", Wingspan: " + wingspan + ", Cargo Capacity: " + cargoCapacity;
    }
}

public class VehicleHierarchy {
    public static void main(String[] args) {
        Taxi taxi = new Taxi( 4);
        Truck truck = new Truck(18, 40000.0);
        Tanker tanker = new Tanker(500000.0,4);
        Boat boat = new Boat(150.0, 0);
        Helicopter helicopter = new Helicopter(3, 1, 1000.0);
        Airplane airplane = new Airplane(3, 16, 15000, 3);

        System.out.println(taxi);
        System.out.println(truck);
        System.out.println(tanker);
        System.out.println(boat);
        System.out.println(helicopter);
        System.out.println(airplane);
    }
}
