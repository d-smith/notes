package ds.org.helloflink;

import org.apache.flink.api.java.tuple.Tuple4;
import static org.apache.flink.table.api.Expressions.$;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class CustomerPurchasesProcessing {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        DataStream<Tuple4<String, String, Integer, String>> inputStream = env.fromElements(
                Tuple4.of("William", "TV", 1500, "Amazon"),
                Tuple4.of("William", "iPad", 499, "Walmart"),
                Tuple4.of("John", "Fitbit", 359, "Amazon"),
                Tuple4.of("Tom", "Samsung Galaxy", 556, "Target"),
                Tuple4.of("Tom", "Headphones", 89, "Amazon"),
                Tuple4.of("Alvin", "Smart Wi-Fi Camera", 99, "Walmart"),
                Tuple4.of("Kevin", "Airpods", 199, "Apple Store"),
                Tuple4.of("Kevin", "Headphones", 53, "Walmart"),
                Tuple4.of("Henry", "Watch", 128, "Target"));

        tableEnv.createTemporaryView("CustomerPurchases",
                inputStream, $("Name"), $("Product"), $("Price"), $("Store"));

        Table details =  tableEnv.from("CustomerPurchases");


        /* Example 1
        Table purchasedDetails = details.select($("*"));
         */

        /* Example 2
        Table purchasedDetails = details.select($("*"))
                                    .filter($("Store").isEqual("Walmart"));
        */

        //TableResult result = purchasedDetails.execute();

        //Example 3
        TableResult result = tableEnv.sqlQuery("select * from CustomerPurchases where Price > 200").execute();


        result.print();
    }


}
