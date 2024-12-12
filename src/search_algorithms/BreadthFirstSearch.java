package search_algorithms;

import problem_definition.State;

import java.util.ArrayDeque;
import java.util.List;

public class BreadthFirstSearch extends SearchAlgorithm {
    private final ArrayDeque<State> queue = new ArrayDeque<>();

    public BreadthFirstSearch(State initialState) {
        super(initialState);
    }

    @Override
    public void search() {
        State currentState = initialState;
        while (shouldContinueSearch(currentState)) {
            processState(currentState);
            currentState = queue.poll();
        }
    }

    private boolean shouldContinueSearch(State currentState) {
        return currentState != null && !isSolutionFound();
    }

    private void processState(State currentState) {
        incrementNodesExpanded();
        List<State> successors = expand(currentState);
        applyGoalTest(successors);
        queue.addAll(successors);
    }
}
