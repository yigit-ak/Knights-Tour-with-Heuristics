package search_algorithms;

import problem_definition.State;

import java.util.ArrayDeque;
import java.util.List;

public class BreadthFirstSearch extends SearchAlgorithm {
    private final ArrayDeque<State> queue = new ArrayDeque<>();

    public BreadthFirstSearch(State initialState) {
        super(initialState);
        queue.add(initialState);
    }

    @Override
    public void search() {
        int iterationCount = 0; // Initialize counter
        while (!isSolutionFound() && !queue.isEmpty()) { // stop if solution found or queue is empty
            iterationCount++; // Increment counter
            State currentState = queue.pop();
            List<State> successors = expand(currentState);
            applyGoalTest(successors);
            queue.addAll(successors);
            System.out.println("While loop iterated " + iterationCount + " times."); // Log the count
        }
    }
}
