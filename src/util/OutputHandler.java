package util;

import experiment.ExperimentInstance;
import problem_definition.Location;
import search_algorithms.SearchAlgorithm;

import java.util.List;

public class OutputHandler {
    public static void experimentResultToConsole(ExperimentInstance experiment) {
        SearchAlgorithm searchAlgorithm = experiment.getSearchAlgorithm();
        System.out.println("Search algorithm: " + experiment.getSearchAlgorithmName());
        System.out.println("Time limit: " + SearchAlgorithm.TIME_LIMIT);
        System.out.println("Board size: " + searchAlgorithm.getBoardSize());
        if (!experiment.getErrorMessage().isPresent()) {
            if (searchAlgorithm.isSolutionFound()) {
                System.out.println("Status: A solution found.");
                solutionPathToConsole(searchAlgorithm.getSolutionPath());
                System.out.println("Time spent: " + experiment.getSearchTime());
            } else {
                System.out.println("Status: No solution exists.");
            }
        } else {
            System.out.println("Status: " + experiment.getErrorMessage().get());
        }
        System.out.println("Number of extended nodes: " + searchAlgorithm.getExpandedNodeCount());
    }

    public static void experimentResultToFile(ExperimentInstance experiment) {
        // this method prints the experiment result in a file
        // TODO: implementation
    }

    public static void solutionPathToFile(List<Location> solutionPath) {
        // this method prints the solution path in a file
        // the file is to be used for visualization of the solution
        // the format should be like: 0, 0 \n 2, 1 \n 4, 2 \n ...
        // IN A SEPARATE FILE!!!!!!!
        // TODO: implementation
    }

    public static void solutionPathToConsole(List<Location> solutionPath) {
        System.out.print("Solution: ");
        // the format should be like: (0,0), (2,1), (3,4)...
        // TODO: implementation
    }
}
