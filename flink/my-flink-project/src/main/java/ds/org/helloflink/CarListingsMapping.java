package ds.org.helloflink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.InvalidPropertiesFormatException;

public class CarListingsMapping {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        env.socketTextStream("localhost", 9000)
                .map(new CreateCarObjects())
                .filter(new MakePriceFilter("Ford", 20000f))
                .map(new MapFunction<Car, Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> map(Car car) throws Exception {
                        return new Tuple3<>(car.make, car.model, car.type);
                    }
                })
                .print();

        env.execute();
    }

    public static class CreateCarObjects implements MapFunction<String, Car> {

        @Override
        public Car map(String carString) throws Exception {

            String[] tokens = carString.split(",");

            if (tokens.length < 4) {
                throw new InvalidPropertiesFormatException("Invalid stream input: " + carString);
            }

            return new Car(tokens[0].trim(),
                    tokens[1].trim(),
                    tokens[2].trim(),
                    Float.parseFloat(tokens[3].trim()));
        }
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
