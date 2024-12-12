import search_algorithms.*;
import problem_definition.*;

public class Main {
    public static void main(String[] args) {
        ExperimentConfig config = new ExperimentConfig(8, 'a', 900); // Example configuration

        try {
            SearchAlgorithm searchAlgorithm = createSearchAlgorithm(config.getBoardSize(), config.getMethod());

            long startTime = System.currentTimeMillis();
            searchAlgorithm.search();
            long endTime = System.currentTimeMillis();

            printResults(searchAlgorithm, config.getMethod(), config.getTimeLimit(), endTime - startTime);
        } catch (OutOfMemoryError e) {
            System.err.println("Error: Out of memory.");
        }
    }

    private static SearchAlgorithm createSearchAlgorithm(int n, char method) {
        State initialState = new State(new boolean[n][n], new Location(0, 0));
        return switch (method) {
            case 'a' -> new BreadthFirstSearch(initialState);
            case 'b' -> new DepthFirstSearch(initialState);
            case 'c' -> new DfsWithHeuristicH1B(initialState);
            case 'd' -> new DfsWithHeuristicH2(initialState);
            default -> throw new IllegalArgumentException("Invalid search method");
        };
    }

    private static void printResults(SearchAlgorithm searchAlgorithm, char method, int timeLimit, long timeSpent) {
        System.out.println("Search method: " + method);
        System.out.println("Time limit: " + timeLimit + " seconds");

        if (searchAlgorithm.isSolutionFound()) {
            System.out.println("A solution found.");
            System.out.println("Solution path: " + searchAlgorithm.getSolutionPath());
            System.out.println("Time spent: " + timeSpent + " milliseconds");
        } else {
            System.out.println("No solution exists.");
        }
    }
}