package search_algorithms;

import problem_definition.State;
import problem_definition.Location;

import java.util.List;
import java.util.Comparator;
import java.util.Stack;
import java.util.ArrayList;

public class DfsWithHeuristicH1B extends SearchAlgorithm {
    private final Stack<State> stack = new Stack<>();

    public DfsWithHeuristicH1B(State initialState) {
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

            // Apply heuristic h1b: sort successors by Warnsdorff's rule
            try {
                successors = new ArrayList<>(successors); // Ensure the list is mutable
                successors.sort(Comparator.comparingInt(this::calculateH1b));
            } catch (UnsupportedOperationException e) {
                System.err.println("Sorting operation is not supported: " + e.getMessage());
            }

            // Push sorted successors onto the stack
            stack.addAll(successors);
            System.out.println("While loop iterated " + iterationCount + " times."); // Log the count
        }
    }

    private int calculateH1b(State state) {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());
        return availableMoves.size(); // Number of onward moves (lower is better)
    }
}
