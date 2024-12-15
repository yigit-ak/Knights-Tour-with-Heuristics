package search_algorithms;

import problem_definition.State;
import problem_definition.Location;

import java.util.List;
import java.util.Comparator;
import java.util.Stack;

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
            successors.sort(Comparator.comparingInt(this::calculateH2));

            for (State successor : successors) {
                stack.push(successor); // Push successors onto the stack
            }
        }
    }

    private int calculateH2(State state) {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());

        // h1b: Number of onward moves (lower is better)
        int onwardMoves = availableMoves.size();

        // h2: Distance to corners (lower is better)
        int distanceToCorners = calculateDistanceToCorners(lastPlacedKnight, state.board().length);

        // Combine heuristics (e.g., prioritize onward moves, then resolve ties with
        // distance)
        return onwardMoves * 10 + distanceToCorners; // Weighted sum, adjust weights if needed
    }

    /**
     * Calculates the minimum Manhattan distance from a location to any corner of
     * the board.
     *
     * @param location  The current knight's location.
     * @param boardSize The size of the board (n x n).
     * @return The minimum distance to any corner.
     */
    private int calculateDistanceToCorners(Location location, int boardSize) {
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
