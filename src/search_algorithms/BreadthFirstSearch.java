package search_algorithms;

import problem_definition.State;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BreadthFirstSearch extends SearchAlgorithm {
    private final ArrayDeque<State> queue = new ArrayDeque<>();

    public BreadthFirstSearch(State initialState) {
        super(initialState);
        queue.add(initialState);
    }

    @Override
    public void search() throws TimeoutException, RuntimeException {
        try {
            while (!isSolutionFound() && !queue.isEmpty()) { // stop if solution found or queue is empty
                State currentState = queue.pop();
                List<State> successors = expand(currentState);
                applyGoalTest(successors);
                queue.addAll(successors);
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime > TIME_LIMIT) {
                    throw new TimeoutException();
                }
            }
        } catch (OutOfMemoryError e) {
            this.initialState = null;
            System.gc();
            throw new RuntimeException();
        }
    }
}
