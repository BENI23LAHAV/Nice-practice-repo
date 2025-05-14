import java.util.Random;

public abstract class Vehicle implements MoveAble {
    protected String model;
    protected int year;

    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Model: " + model + ", Year: " + year);
    }

    public abstract void fuelType();

    public abstract double costPer100Km();




    public static void main(String[] args) {
        Vehicle[] vehicles = {new Car("Subaru", 2015), new Car("Toyota", 2013),
                new ElectricScooter("xiomi", 2022), new ElectricScooter("Sumsung", 2023),
                new Bike("Trek", 2020), new Bike("Marida", 2022)};
int sumOfEfficiencyScore = 0;
Vehicle min = vehicles[0];
        for (Vehicle vehicle : vehicles){
            vehicle.displayInfo();vehicle.fuelType();vehicle.move();
            System.out.println("Efficiency: " + vehicle.efficiencyScore());

            System.out.println("Cost per 100 km: " + vehicle.costPer100Km());
            System.out.println();
            sumOfEfficiencyScore+= vehicle.efficiencyScore();
            min = vehicle.costPer100Km()<min.costPer100Km() ? vehicle : min;
        }
        System.out.println("sumOfEfficiencyScore: "+((double)(sumOfEfficiencyScore/vehicles.length)));
        System.out.println();
        System.out.println("Min costPer100Km vehicle: "+min.model+" - "+min.year+"\n costPer100Km: "+min.costPer100Km());
    }
}


interface MoveAble {
    void move();

    int efficiencyScore();
}


class Car extends Vehicle {


    Car(String model, int year) {
        super(model, year);
    }

    public void fuelType() {
        System.out.println("Gasoline");
    }

    public double costPer100Km() {
        return 7 * 10;
    }

    public void move() {
        System.out.println("Car is driving");
    }

    public int efficiencyScore() {
        return new Random().nextInt(15) + 6;
    }


}

class ElectricScooter extends Vehicle {
    ElectricScooter(String model, int year) {
        super(model, year);
    }

    public void fuelType() {
        System.out.println("Electric");
    }

    public double costPer100Km() {
        return 5;
    }

    public void move() {
        System.out.println("Scooter is gliding");
    }

    public int efficiencyScore() {
        return new Random().nextInt(10) + 90;
    }
}

class Bike extends Vehicle {
    Bike(String model, int year) {
        super(model, year);
    }

    public void fuelType() {
        System.out.println("Human-powered");
    }

    public double costPer100Km() {
        return 0;
    }

    public void move() {
        System.out.println("Bike is pedaling");
    }

    public int efficiencyScore() {
        return 100;
    }
}




