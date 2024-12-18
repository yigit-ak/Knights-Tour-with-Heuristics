package util;

import search_algorithms.SearchAlgorithm;

public class ExceptionHandler {
    public static void outOfMemoryExceptionHandler(SearchAlgorithm sa) {
        sa.nullifyStates();
        System.gc();
    }
}
