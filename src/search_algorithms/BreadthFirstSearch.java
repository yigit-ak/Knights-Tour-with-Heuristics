package search_algorithms;

import problem_definition.State;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BreadthFirstSearch extends SearchAlgorithm {
    private final ArrayDeque<State> queue = new ArrayDeque<>();
    private static final double MEMORY_THRESHOLD = 0.9; // 90% memory usage threshold

    public BreadthFirstSearch(State initialState) {
        super(initialState);
        queue.add(initialState);
    }

    @Override
    public void search() throws TimeoutException, OutOfMemoryError {
        int iterationCount = 0;
        try {
            while (!isSolutionFound() && !queue.isEmpty()) { // stop if solution found or queue is empty
                iterationCount++;
                State currentState = queue.pop();
                List<State> successors = expand(currentState);
                applyGoalTest(successors);
                queue.addAll(successors);

                long currentTime = System.currentTimeMillis();
                if (iterationCount % 100000 == 0) {
                    System.out.println("Iteration: " + iterationCount + " | Queue size: " + queue.size());
                    System.out
                            .println("Time Remaining: " + (TIME_LIMIT - (currentTime - startTime)) / 1000 + " seconds");
                }
                if (currentTime - startTime > TIME_LIMIT) {
                    throw new TimeoutException();
                }

                // Check memory usage
                if (getMemoryUsage() > MEMORY_THRESHOLD) {
                    System.out.println("\nMemory usage exceeded threshold!");
                    System.gc();
                    throw new OutOfMemoryError();
                }
            }
        } catch (TimeoutException e) {
            System.out.println("\nTime limit exceeded!");
            throw e;
        } catch (OutOfMemoryError e) {
            System.out.println("\nMemory limit exceeded!");
            System.gc();
            throw e;
        }
    }

    private double getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return (double) (runtime.totalMemory() - runtime.freeMemory()) / runtime.maxMemory();
    }
}
