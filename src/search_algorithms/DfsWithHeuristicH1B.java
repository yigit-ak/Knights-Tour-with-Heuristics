package search_algorithms;

import problem_definition.State;
import problem_definition.Location;

import java.util.List;
import java.util.Comparator;

public class DfsWithHeuristicH1B extends SearchAlgorithm {
    public DfsWithHeuristicH1B(State initialState) {
        super(initialState);
    }

    @Override
    public void search() {
        dfsWithHeuristic(initialState);
    }

    private boolean dfsWithHeuristic(State currentState) {
        incrementNodesExpanded();
        if (currentState.isSolution()) {
            this.solution = currentState;
            return true;
        }

        List<State> successors = expand(currentState);
        applyGoalTest(successors);

        // Apply heuristic h1b: sort successors by Warnsdorff's rule
        successors.sort(Comparator.comparingInt(this::calculateH1b));

        for (State successor : successors) {
            if (dfsWithHeuristic(successor)) {
                return true;
            }
        }

        return false;
    }

    private int calculateH1b(State state) {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());
        return availableMoves.size(); // Number of onward moves (lower is better)
    }
}
