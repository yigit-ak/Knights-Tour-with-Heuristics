package experiment;

import exceptions.MemoryExceedException;
import exceptions.TimeExceedException;
import search_algorithms.SearchAlgorithm;

import java.util.Optional;

import static util.ExceptionHandler.outOfMemoryExceptionHandler;
import static util.OutputHandler.experimentResultToConsole;

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
        }
    }

    public SearchAlgorithm getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public long getSearchTime() {
        return searchTime;
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }
}
