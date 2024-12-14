package util;

import experiment.ExperimentInstance;
import problem_definition.Location;
import search_algorithms.SearchAlgorithm;

import java.util.List;
import java.util.Locale;

public class OutputHandler {
    public static void experimentResultToConsole(ExperimentInstance experiment) {
        // this method prints the experiment result on the console
        // TODO: implementation
    }

    public static void experimentResultToFile(ExperimentInstance experiment) {
        // this method prints the experiment result in a file
        // TODO: implementation
    }

    public static void solutionPathToFile(SearchAlgorithm searchAlgorithm) {
        List<Location> solutionPath = searchAlgorithm.getSolutionPath();
        // this method prints the solution path in a file
        // the file is to be used for visualization of the solution
        // the format should be like: 0, 0 \n 2, 1 \n 4, 2 \n ...
        // TODO: implementation
    }
}
