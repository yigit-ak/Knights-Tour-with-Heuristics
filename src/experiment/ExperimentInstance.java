package experiment;

import search_algorithms.SearchAlgorithm;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static util.OutputHandler.experimentResultToConsole;

public class ExperimentInstance {
    final SearchAlgorithm searchAlgorithm;
    private String errorMessage;
    private long searchTime;

    public ExperimentInstance(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void runSearch() {
        try {
            searchAlgorithm.search();
        } catch (TimeoutException e) {
            errorMessage = "Timeout";
        } catch (OutOfMemoryError e) {
            errorMessage = "Out of Memory";
        } catch (Exception e) {
            errorMessage = "Exception";
        } finally {
            long endTime = System.currentTimeMillis();
            searchTime = endTime - searchAlgorithm.getStartTime();
            experimentResultToConsole(this);
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

    public long getSearchTime() {
        return searchTime;
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }
}
