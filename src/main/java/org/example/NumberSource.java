package org.example;

import java.util.List;

/**
 * Defines a data source that provides integer values for statistics calculation.
 */
public interface NumberSource {
    /**
     * Loads numbers that should be processed. Implementations may fetch data from
     * anywhere (database, file, network, etc.).
     *
     * @return list of integers; may be empty but never null
     */
    List<Integer> load();
}

