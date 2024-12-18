package search_algorithms;

import problem_definition.State;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends SearchAlgorithm {
    private final Stack<State> stack = new Stack<>();

    public DepthFirstSearch(State initialState) {
        super(initialState);
        stack.push(initialState); // Initial state is pushed onto the stack
    }

    @Override
    public void search() {
        int iterationCount = 0; // Initialize counter
        while (!stack.isEmpty() && !isSolutionFound()) {
            iterationCount++; // Increment counter
            State currentState = stack.pop(); // Pop the most recent state (LIFO)
            List<State> successors = expand(currentState);
            applyGoalTest(successors);
            stack.addAll(successors);
            System.out.println("While loop iterated " + iterationCount + " times."); // Log the count
        }
    }
}
