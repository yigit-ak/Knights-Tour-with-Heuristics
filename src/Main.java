import search_algorithms.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] boardSizes = {8, 16, 32, 41, 52};

        Class<?>[] searchAlgorithmsClasses = {
                BreadthFirstSearch.class,
                DepthFirstSearch.class,
                DfsWithWarnsdorffRule.class,
                DfsWithEnhancedWarnsdorffRule.class
        };

        for (Class<?> searchAlgorithmClass : searchAlgorithmsClasses) {
            try {
                SearchAlgorithm searchAlgorithm = (SearchAlgorithm) searchAlgorithmClass.getConstructor().newInstance();
                testSearchAlgorithm(searchAlgorithm, boardSizes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void testSearchAlgorithm(SearchAlgorithm searchAlgorithm, int[] boardSizesToTest) {
        for (int boardSize : boardSizesToTest) {

        }
    }
}
