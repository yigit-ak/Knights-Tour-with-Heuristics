package experiment;

import exceptions.MemoryExceedException;
import exceptions.TimeExceedException;
import search_algorithms.SearchAlgorithm;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static util.ExceptionHandler.outOfMemoryExceptionHandler;
import static util.OutputHandler.experimentResultToConsole;
import static util.OutputHandler.experimentResultToFile;

public class ExperimentInstance {
    public static long expandedNodeCount;
    final SearchAlgorithm searchAlgorithm;
    private String errorMessage;
    private long searchTime;

    public ExperimentInstance(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void runSearch() {
        try {
            searchAlgorithm.search();
        } catch (MemoryExceedException e) {
            outOfMemoryExceptionHandler(searchAlgorithm);
            errorMessage = "Out of memory";
        } catch (TimeExceedException e) {
            errorMessage = "Timeout";
        } finally {
            long endTime = System.currentTimeMillis();
            searchTime = endTime - searchAlgorithm.getStartTime();
            experimentResultToConsole(this);
            experimentResultToFile(this);
        }
    }

    public SearchAlgorithm getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public String getSearchAlgorithmName() {
        switch (searchAlgorithm.getClass().getName()) {
            case "search_algorithms.BreadthFirstSearch":
                return "Breadth First Search";
            case "search_algorithms.DepthFirstSearch":
                return "Depth First Search";
            case "search_algorithms.DfsWithWarnsdorffRule":
                return "Depth First Search with Heuristic H1B";
            case "search_algorithms.DfsWithEnhancedWarnsdorffRule":
                return "Depth First Search with Heuristic H2";
            default:
                return "Unknown";
        }
    }

    public String getShortAlgorithmName() {
        switch (searchAlgorithm.getClass().getName()) {
            case "search_algorithms.BreadthFirstSearch":
                return "BFS";
            case "search_algorithms.DepthFirstSearch":
                return "DFS";
            case "search_algorithms.DfsWithWarnsdorffRule":
                return "DFS_H1B";
            case "search_algorithms.DfsWithEnhancedWarnsdorffRule":
                return "DFS_H2";
            default:
                return "Unknown";
        }
    }

    public long getSearchTime() {
        return searchTime;
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }
}
