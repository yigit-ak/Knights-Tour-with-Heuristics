package search_algorithms;

import problem_definition.State;
import problem_definition.Location;

import java.util.List;
import java.util.Comparator;
import java.util.Stack;

public class DfsWithHeuristicH1B extends SearchAlgorithm {
    private final Stack<State> stack = new Stack<>();

    public DfsWithHeuristicH1B(State initialState) {
        super(initialState);
        stack.push(initialState); // Initial state is pushed onto the stack
    }

    @Override
    public void search() {
        while (!stack.isEmpty() && !isSolutionFound()) {
            State currentState = stack.pop(); // Pop the most recent state (LIFO)
            List<State> successors = expand(currentState);
            applyGoalTest(successors);

            // Apply heuristic h1b: sort successors by Warnsdorff's rule
            successors.sort(Comparator.comparingInt(this::calculateH1b));

            for (State successor : successors) {
                stack.push(successor); // Push successors onto the stack
            }
        }
    }

    private int calculateH1b(State state) {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());
        return availableMoves.size(); // Number of onward moves (lower is better)
    }
}
