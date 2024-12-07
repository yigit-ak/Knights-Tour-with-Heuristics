package search_algorithms;

import problem_definition.Location;
import problem_definition.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

abstract public class SearchAlgorithm {
    protected final State initialState;

    public SearchAlgorithm(State initialState) {
        this.initialState = initialState;
    }

    abstract public void search();

    public List<State> expand(State state) {
        Location lastPlacedKnight = state.locationOfLastPlacedKnight();
        List<Location> availableMoves = lastPlacedKnight.getLocationsForNextMove(state.board());

        return availableMoves.stream().map(state::addKnightAt).toList();
    }

    public List<Location> getSolutionPath(State solution) {
        List<Location> solutionPath = new LinkedList<>();

        Optional<State> currentState = Optional.of(solution);
        do {
            Location step = currentState.get().locationOfLastPlacedKnight();
            solutionPath.addFirst(step);
            currentState = currentState.get().parent();
        } while (currentState.isPresent());

        return solutionPath;
    }
}
