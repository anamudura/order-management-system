package bll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseProduct extends MenuItem{
    private static final long  serialVersionUID = 2243021096500390782L;
    public BaseProduct(String title, float rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
    }

    public BaseProduct() {

    }


    public static void ReadFile()
    {
        List<MenuItem> items = new ArrayList<MenuItem>();
        File file = new File("D:/Lab PT/PT2022_30221_Mudura_Ana_assigment_4/src/main/java/products.csv");
        try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(file)))) {
            List<List<String>> values = lines.skip(1).map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
            values.stream().filter(distinctByKey(p -> p.get(0))).distinct();
            for (List<String> value : values) {
                float rating = Float.parseFloat(value.get(1));
                int calories = Integer.parseInt(value.get(2));
                int proteins = Integer.parseInt(value.get(3));
                int fats = Integer.parseInt(value.get(4));
                int sodium = Integer.parseInt(value.get(5));
                int price = Integer.parseInt(value.get(6));
                items.add(new BaseProduct(value.get(0), rating, calories, proteins, fats, sodium, price));
                //System.out.println(rating +" "+ calories+" " + proteins+" " + fats+" " + sodium+" " + price);
            }
            ArrayList<MenuItem> fin = (ArrayList<MenuItem>) items.stream().filter(distinctByKey(MenuItem::getTitle)).collect(Collectors.toList());
            DeliveryService.setMenu(fin);
        } catch (IOException e) {
            System.out.println("EROARE CITIRE DIN FISIER");
            e.printStackTrace();
        }
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    @Override
    public void setPrice(int price) {
        super.setPrice(price);
    }
    @Override
    public float computePrice() {
        return getPrice();
    }
}
