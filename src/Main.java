import search_algorithms.*;
import problem_definition.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board size (n): ");
        int n = scanner.nextInt();

        System.out.print("Enter search method (a-d): ");
        char method = scanner.next().charAt(0);

        System.out.print("Enter time limit (t) in seconds: ");
        int timeLimit = scanner.nextInt();

        scanner.close();

        SearchAlgorithm searchAlgorithm = createSearchAlgorithm(n, method);

        long startTime = System.currentTimeMillis();
        searchAlgorithm.search();
        long endTime = System.currentTimeMillis();

        printResults(searchAlgorithm, method, timeLimit, endTime - startTime);
    }

    private static SearchAlgorithm createSearchAlgorithm(int n, char method) {
        State initialState = new State(new boolean[n][n], new Location(0, 0));
        return switch (method) {
            case 'a' -> new BreadthFirstSearch(initialState);
            case 'b' -> new DepthFirstSearch(initialState);
            case 'c' -> new DfsWithWarnsdorffRule(initialState);
            case 'd' -> new DfsWithEnhancedWarnsdorffRule(initialState);
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

        // TODO: Implement this method in SearchAlgorithm.java
        // System.out.println("Number of nodes expanded: " +
        // searchAlgorithm.getNumberOfNodesExpanded());
    }
}
