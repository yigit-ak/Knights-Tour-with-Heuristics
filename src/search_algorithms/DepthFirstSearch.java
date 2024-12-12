package search_algorithms;

import problem_definition.State;

import java.util.Stack;
import java.util.List;

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
            processState(currentState);
        }
    }

    private void processState(State currentState) {
        incrementNodesExpanded();
        List<State> successors = expand(currentState);
        applyGoalTest(successors);
        pushSuccessorsToStack(successors); // Push successors onto the stack
    }

    private void pushSuccessorsToStack(List<State> successors) {
        for (State successor : successors) {
            stack.push(successor); // Successors are pushed onto the stack
        }
    }
}
