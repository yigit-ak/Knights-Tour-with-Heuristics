package search_algorithms;

import problem_definition.Location;
import problem_definition.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static experiment.ExperimentInstance.expandedNodeCount;

abstract public class SearchAlgorithm {
    public static final long TIME_LIMIT = 15 * 60 * 1000; // 15 minute
    protected State initialState;
    protected State solution;
    protected long startTime = System.currentTimeMillis();
    protected int boardSize;

    public SearchAlgorithm(State initialState) {
        this.initialState = initialState;
        expandedNodeCount = 1;
        boardSize = initialState.board().length;
    }

    abstract public void search();

    protected List<State> expand(State state) throws OutOfMemoryError {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());
        incrementExpandedNodeCount(availableMoves.size());
        return availableMoves.stream().map(state::addKnightAt).toList();
    }

    protected void applyGoalTest(Collection<State> states) {
        for (State state : states)
            applyGoalTest(state);
    }

    protected void applyGoalTest(State state) {
        if (state.isSolution()) this.solution = state;
    }

    public boolean isSolutionFound() {
        return solution != null;
    }

    public List<Location> getSolutionPath() {
        List<Location> solutionPath = new LinkedList<>();

        // start from leaf, go until the root
        Optional<State> currentState = Optional.of(solution);
        while (currentState.isPresent()) {
            Location step = currentState.get().locationOfLastPlacedKnight();
            solutionPath.addFirst(step);
            currentState = currentState.get().parent();
        }

        return solutionPath;
    }

    public int getBoardSize() {
        return boardSize;
    }

    protected void incrementExpandedNodeCount(int nodeCount) {
        expandedNodeCount += nodeCount;
    }

    public long getStartTime() {
        return startTime;
    }

    public void nullifyStates() {
        initialState = null;
        solution = null;
    }
}
