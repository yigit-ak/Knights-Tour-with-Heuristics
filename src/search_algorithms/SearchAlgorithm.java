package search_algorithms;

import problem_definition.Location;
import problem_definition.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

abstract public class SearchAlgorithm {
    public static final long TIME_LIMIT = 1 * 60 * 1000; // 1 minute
    protected State initialState;
    protected State solution;
    protected int expandedNodeCount = 1;
    protected long startTime = System.currentTimeMillis();

    public SearchAlgorithm(State initialState) {
        this.initialState = initialState;
    }

    abstract public void search() throws TimeoutException;

    protected List<State> expand(State state) throws OutOfMemoryError {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());

        incrementExpandedNodeCount(availableMoves.size());
        try {
            return availableMoves.stream().map(state::addKnightAt).toList();
        } catch (OutOfMemoryError e) {
            this.initialState = null;
            System.gc();
            throw new OutOfMemoryError("Ran out of memory while expanding the state");
        }
    }

    protected void applyGoalTest(Collection<State> states) {
        for (State state : states)
            applyGoalTest(state);
    }

    protected void applyGoalTest(State state) {
        if (state.isSolution())
            this.solution = state;
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
        return initialState == null ? 0 : initialState.board().length;
    }

    public int getExpandedNodeCount() {
        return this.expandedNodeCount;
    }

    protected void incrementExpandedNodeCount(int nodeCount) {
        this.expandedNodeCount += nodeCount;
    }

    public long getStartTime() {
        return startTime;
    }
}
