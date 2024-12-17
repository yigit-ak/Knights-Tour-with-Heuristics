package search_algorithms;

import problem_definition.State;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeoutException;

public class DepthFirstSearch extends SearchAlgorithm {
    private final Stack<State> stack = new Stack<>();
    private static final double MEMORY_THRESHOLD = 0.9; // 90% memory usage threshold

    public DepthFirstSearch(State initialState) {
        super(initialState);
        stack.push(initialState); // Initial state is pushed onto the stack
    }

    @Override
    public void search() throws TimeoutException, OutOfMemoryError {
        try {
            while (!stack.isEmpty() && !isSolutionFound()) {
                State currentState = stack.pop(); // Pop the most recent state (LIFO)
                List<State> successors = expand(currentState);
                applyGoalTest(successors);
                stack.addAll(successors);

                long currentTime = System.currentTimeMillis();
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
