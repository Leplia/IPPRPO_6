package org.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        NumberSource source = () -> java.util.List.of(5, -1, 3, 0, 12);
        StatisticsService service = new StatisticsService(source);

        System.out.println("Numbers: " + source.load());
        System.out.println("Sum: " + service.sum());
        System.out.println("Average: " + service.average());
        System.out.println("Min: " + service.min());
        System.out.println("Max: " + service.max());
        System.out.println("Range: " + service.range());
        System.out.println("Top 3: " + service.topN(3));
    }
}
