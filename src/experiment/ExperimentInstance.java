package experiment;

import search_algorithms.SearchAlgorithm;

import java.util.Optional;
import java.util.concurrent.*;

import static util.OutputHandler.experimentResultToConsole;

public class ExperimentInstance {
    final static long TIME_THRESHOLD = 15; // in minutes

    final SearchAlgorithm searchAlgorithm;
    private long searchTime; // in milliseconds
    private ScheduledExecutorService scheduler;
    private String errorMessage;

    public ExperimentInstance(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public void runSearch() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> futureTask = null;
        long startTime = System.currentTimeMillis();

        try {
            futureTask = executor.submit(searchAlgorithm::search);
            futureTask.get(TIME_THRESHOLD, TimeUnit.MINUTES);
        } catch (OutOfMemoryError e) {
            errorMessage = "Out of Memory";
            experimentResultToConsole(this);
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            errorMessage = "Timeout";
            experimentResultToConsole(this);
        } finally {
            if (futureTask != null && !futureTask.isDone()) {
                futureTask.cancel(true);
            }
            executor.shutdown();
        }

        long endTime = System.currentTimeMillis();
        searchTime = endTime - startTime;
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
