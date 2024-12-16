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
        } catch (OutOfMemoryError e) {
            errorMessage = "Out of memory";
        } catch (TimeoutException e) {
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
