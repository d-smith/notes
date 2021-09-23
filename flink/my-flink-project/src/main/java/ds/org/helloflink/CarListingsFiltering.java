package ds.org.helloflink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CarListingsFiltering {
    public static class Car {
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

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Car> dataStream = env.fromElements(
                new Car("BMW", "745i 4dr", "Sedan", 69195f),
                new Car("BMW", "745Li 4dr", "Sedan", 73195f),
                new Car("BMW", "545", "Sedan", 39195f),
                new Car("Chevrolet", "Cavalier 2dr", "Sedan", 14610f),
                new Car("Chevrolet", "Avalanche 1500", "Truck", 36100f),
                new Car("Chevrolet", "Cruz", "Sedan", 16400f),
                new Car("Ford", "Excursion 6.8 XLT", "SUV", 41475f),
                new Car("Ford", "Focus LX 4dr", "Sedan", 13730f),
                new Car("Ford", "Figo", "Sedan", 10730f),
                new Car("Honda", "Civic Hybrid 4dr manual", "Hybrid", 20140f));

        dataStream.filter(new MakePriceFilter("BMW", 70000f))
                .print();

        env.execute();
    }

    public static class MakePriceFilter implements FilterFunction<Car> {

        private String make;
        private Float price;

        public MakePriceFilter(String make, Float price) {
            this.make = make;
            this.price = price;
        }

        @Override
        public boolean filter(Car car) throws Exception {
            return (car.make).equals(make) && (car.price < price);
        }
    }

}
