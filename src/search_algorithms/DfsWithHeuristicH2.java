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
                            .thenComparingInt(this::calculateDistanceToCorners) // Break ties if equal (h2)
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

    private int calculateDistanceToCorners(State state) {
        Location location = state.locationOfLastPlacedKnight();
        int boardSize = state.board().length;
        int row = location.row();
        int col = location.column();

        int[] cornerDistances = new int[] {
                row + col, // Top-left corner (0, 0)
                row + (boardSize - 1 - col), // Top-right corner (0, n-1)
                (boardSize - 1 - row) + col, // Bottom-left corner (n-1, 0)
                (boardSize - 1 - row) + (boardSize - 1 - col) // Bottom-right corner (n-1, n-1)
        };

        int minDistance = Integer.MAX_VALUE;
        for (int distance : cornerDistances) {
            minDistance = Math.min(minDistance, distance);
        }

        return minDistance;
    }
}