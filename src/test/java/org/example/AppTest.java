package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppTest {

    @Mock
    private NumberSource numberSource;

    private StatisticsService service;

    @BeforeEach
    void setUp() {
        service = new StatisticsService(numberSource);
    }

    @Test
    void sumCalculatesTotalOfNumbers() {
        when(numberSource.load()).thenReturn(Arrays.asList(1, 2, 3, 4));

        assertEquals(10, service.sum());
        verify(numberSource).load();
    }

    @Test
    void averageCalculatesMean() {
        when(numberSource.load()).thenReturn(Arrays.asList(2, 4, 6, 8));

        assertEquals(5.0, service.average());
        verify(numberSource).load();
    }

    @Test
    void minFindsSmallestValue() {
        when(numberSource.load()).thenReturn(Arrays.asList(5, -10, 3));

        assertEquals(-10, service.min());
        verify(numberSource).load();
    }

    @Test
    void maxFindsLargestValue() {
        when(numberSource.load()).thenReturn(Arrays.asList(5, -10, 3));

        assertEquals(5, service.max());
        verify(numberSource).load();
    }

    @Test
    void containsZeroDetectsZero() {
        when(numberSource.load()).thenReturn(Arrays.asList(3, 0, 9));

        assertTrue(service.containsZero());
        verify(numberSource).load();
    }

    @Test
    void countPositiveCountsOnlyPositiveNumbers() {
        when(numberSource.load()).thenReturn(Arrays.asList(-1, 0, 5, 12, -4));

        assertEquals(2, service.countPositive());
        verify(numberSource).load();
    }

    @Test
    void countNegativeCountsOnlyNegativeNumbers() {
        when(numberSource.load()).thenReturn(Arrays.asList(-1, 0, 5, 12, -4));

        assertEquals(2, service.countNegative());
        verify(numberSource).load();
    }

    @Test
    void topNReturnsSortedDescendingSubset() {
        when(numberSource.load()).thenReturn(Arrays.asList(3, 9, 1, 9, 7));

        List<Integer> result = service.topN(3);

        assertEquals(Arrays.asList(9, 9, 7), result);
        verify(numberSource).load();
    }

    @Test
    void minThrowsWhenDataIsEmpty() {
        when(numberSource.load()).thenReturn(Collections.emptyList());

        assertThrows(IllegalStateException.class, service::min);
        verify(numberSource).load();
    }
}
