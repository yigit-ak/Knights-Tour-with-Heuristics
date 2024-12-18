package util;

import exceptions.MemoryExceedException;
import exceptions.TimeExceedException;
import search_algorithms.SearchAlgorithm;

import static search_algorithms.SearchAlgorithm.TIME_LIMIT;

public class ExceedChecker {
    public static boolean isTimeLimitExceeded(long startTime) {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime > TIME_LIMIT;
    }

    public static boolean isMemoryLimitExceeded() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024) < 1; // less than 1 MB
    }

    public static void checkConstraints(SearchAlgorithm algo) throws MemoryExceedException, TimeExceedException {
        if (isMemoryLimitExceeded()) {
            throw new MemoryExceedException();
        }
        if (isTimeLimitExceeded(algo.getStartTime())) {
            throw new TimeExceedException();
        }
    }
}
