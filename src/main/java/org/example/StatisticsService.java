package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Performs simple statistical operations over numbers provided by a {@link NumberSource}.
 */
public class StatisticsService {

    private final NumberSource numberSource;

    public StatisticsService(NumberSource numberSource) {
        this.numberSource = Objects.requireNonNull(numberSource, "numberSource");
    }

    public boolean isEmpty() {
        return loadNumbers().isEmpty();
    }

    public int sum() {
        return loadNumbers().stream().mapToInt(Integer::intValue).sum();
    }

    public double average() {
        return ensureNotEmpty()
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalStateException("Cannot calculate average of empty data"));
    }

    public int min() {
        return ensureNotEmpty()
                .stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new IllegalStateException("Cannot find minimum of empty data"));
    }

    public int max() {
        return ensureNotEmpty()
                .stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("Cannot find maximum of empty data"));
    }

    public int range() {
        return max() - min();
    }

    public boolean containsZero() {
        return loadNumbers().stream().anyMatch(value -> value == 0);
    }

    public int countPositive() {
        return (int) loadNumbers().stream().filter(value -> value > 0).count();
    }

    public int countNegative() {
        return (int) loadNumbers().stream().filter(value -> value < 0).count();
    }

    public List<Integer> topN(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        List<Integer> sorted = new ArrayList<>(loadNumbers());
        sorted.sort(Comparator.reverseOrder());
        return sorted.stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    private List<Integer> loadNumbers() {
        List<Integer> numbers = numberSource.load();
        if (numbers == null) {
            throw new IllegalStateException("NumberSource returned null");
        }
        return numbers;
    }

    private List<Integer> ensureNotEmpty() {
        List<Integer> numbers = loadNumbers();
        if (numbers.isEmpty()) {
            throw new IllegalStateException("No numbers available");
        }
        return numbers;
    }
}

