package search_algorithms;

import problem_definition.State;

import java.util.List;
import java.util.Stack;

import static util.ExceedChecker.checkConstraints;

public class DepthFirstSearch extends SearchAlgorithm {
    private final Stack<State> stack = new Stack<>();

    public DepthFirstSearch(State initialState) {
        super(initialState);
        stack.push(initialState); // Initial state is pushed onto the stack
    }

    @Override
    public void search() {
        while (!stack.isEmpty() && !isSolutionFound()) {
            State currentState = stack.pop(); // Pop the most recent state (LIFO)
            List<State> successors = expand(currentState);
            applyGoalTest(successors);
            stack.addAll(successors);
            checkConstraints(this);
        }
    }
}