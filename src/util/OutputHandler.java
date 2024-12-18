package util;

import experiment.ExperimentInstance;
import problem_definition.Location;
import search_algorithms.SearchAlgorithm;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OutputHandler {
    private static final String RESULTS_DIR = "results";

    static {
        try {
            Files.createDirectories(Paths.get(RESULTS_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void experimentResultToConsole(ExperimentInstance experiment) {
        SearchAlgorithm searchAlgorithm = experiment.getSearchAlgorithm();
        System.out.println("Search algorithm: " + experiment.getSearchAlgorithmName());
        System.out.println("Time limit: " + SearchAlgorithm.TIME_LIMIT);
        System.out.println("Board size: " + searchAlgorithm.getBoardSize());
        if (!experiment.getErrorMessage().isPresent()) {
            if (searchAlgorithm.isSolutionFound()) {
                System.out.println("Status: A solution found.");
                solutionPathToFile(searchAlgorithm.getSolutionPath(), experiment.getShortAlgorithmName(),
                        searchAlgorithm.getBoardSize());
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
        SearchAlgorithm searchAlgorithm = experiment.getSearchAlgorithm();
        String fileName = String.format("%s_%dx%d_experiment_result.txt",
                experiment.getShortAlgorithmName(),
                searchAlgorithm.getBoardSize(),
                searchAlgorithm.getBoardSize());
        try (FileWriter writer = new FileWriter(RESULTS_DIR + "/" + fileName)) {
            writer.write("Search algorithm: " + experiment.getSearchAlgorithmName() + "\n");
            writer.write("Time limit: " + SearchAlgorithm.TIME_LIMIT + "\n");
            writer.write("Board size: " + searchAlgorithm.getBoardSize() + "\n");
            if (!experiment.getErrorMessage().isPresent()) {
                if (searchAlgorithm.isSolutionFound()) {
                    writer.write("Status: A solution found.\n");
                    writer.write("Solution: " + searchAlgorithm.getSolutionPath().toString() + "\n");
                    writer.write("Time spent: " + experiment.getSearchTime() + "\n");
                } else {
                    writer.write("Status: No solution exists.\n");
                }
            } else {
                writer.write("Status: " + experiment.getErrorMessage().get() + "\n");
            }
            writer.write("Number of extended nodes: " + searchAlgorithm.getExpandedNodeCount() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solutionPathToFile(List<Location> solutionPath, String algorithmName, int boardSize) {
        String fileName = String.format("%s_%dx%d_solution_path.txt", algorithmName, boardSize, boardSize);
        try (FileWriter writer = new FileWriter(RESULTS_DIR + "/" + fileName)) {
            for (Location location : solutionPath) {
                writer.write(location.row() + ", " + location.column() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solutionPathToConsole(List<Location> solutionPath) {
        System.out.print("Solution: ");
        StringBuilder sb = new StringBuilder();
        for (Location location : solutionPath) {
            sb.append("(").append(location.row()).append(",").append(location.column()).append("), ");
        }
        // Remove the last comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        System.out.println(sb.toString());
    }
}
