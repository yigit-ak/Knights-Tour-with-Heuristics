package search_algorithms;

import problem_definition.State;
import problem_definition.Location;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import static util.ExceedChecker.checkConstraints;

public class DfsWithHeuristicH2 extends SearchAlgorithm {
    private final Stack<State> stack = new Stack<>();

    public DfsWithHeuristicH2(State initialState) {
        super(initialState);
        stack.push(initialState);
    }

    @Override
    public void search() {
        while (!stack.isEmpty() && !isSolutionFound()) {
            State currentState = stack.pop(); // Pop the most recent state (LIFO)
            List<State> successors = expand(currentState);
            applyGoalTest(successors);

            // Apply heuristic h2: sort successors by Warnsdorff's rule,
            // breaking ties by distance to corners
            successors = new ArrayList<>(successors); // Ensure the list is mutable
            successors.sort(
                    Comparator // Comparator sorts in ascending order
                            .comparingInt(this::calculateNumberOfAvailableMoves) // Apply warnsdorff's rule (as in h1b)
                            .thenComparingInt(this::calculateDistanceToEdges) // Break ties if equal (h2)
                            .reversed() // Reverse the order to pop smaller values from stack first (LIFO)
            );

            // Push sorted successors onto the stack
            stack.addAll(successors);
            checkConstraints(this);
        }
    }

    private int calculateNumberOfAvailableMoves(State state) {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());
        return availableMoves.size(); // Number of onward moves (lower is better)
    }

    private int calculateDistanceToEdges(State state) {
        Location location = state.locationOfLastPlacedKnight();
        int boardSize = state.board().length;
        int row = location.row();
        int col = location.column();

        int[] edgeDistances = new int[] {
        row,                        // distance to top edge
        boardSize - 1 - row,        // distance to bottom edge
        col,                        // distance to left edge
        boardSize - 1 - col,        // distance to right edge
        };

        int minDistance = Integer.MAX_VALUE;
        for (int distance : edgeDistances) {
            minDistance = Math.min(minDistance, distance);
        }

        return minDistance;
    }
}