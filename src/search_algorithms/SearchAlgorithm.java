package search_algorithms;

import problem_definition.Location;
import problem_definition.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

abstract public class SearchAlgorithm {
    protected final State initialState;
    protected State solution;
    protected int expandedNodeCount = 1;

    public SearchAlgorithm(State initialState) {
        this.initialState = initialState;
    }

    abstract public void search();

    protected List<State> expand(State state) {
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
}
