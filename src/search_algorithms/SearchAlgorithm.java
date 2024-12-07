package search_algorithms;

import problem_definition.KnightLocation;
import problem_definition.State;

import java.util.List;

abstract public class SearchAlgorithm {
    protected final State initialState; // n*n square board
    protected int timeSpent; // time spent to find a solution (in milliseconds)
    protected int totalExpandedNodeCount;
    protected List<KnightLocation> solutionPath; // solution path

    public SearchAlgorithm(State initialState) {
        this.initialState = initialState;
    }

    abstract public void search();

    abstract public List<State> expand(State state);

    public int getTimeSpent() {
        return this.timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getExpandedNodeCount() {
        return this.totalExpandedNodeCount;
    }

    public void increaseTotalExpandedNodeCountBy(int expandedNodeCount) {
        totalExpandedNodeCount += expandedNodeCount;
    }

    public List<KnightLocation> getSolutionPath() {
        return solutionPath;
    }

    public void addStepToSolutionPath(KnightLocation newLocation) {
        solutionPath.add(newLocation);
    }

    public void setSolutionPath(List<KnightLocation> newSolutionPath) {
        solutionPath = newSolutionPath;
    }
}
