package ds.org.helloflink;

public class Car {
    public String make;
    public String model;
    public String type;
    public float price;

    public Car() {
    }

    public Car(String make, String model, String type, float price) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
