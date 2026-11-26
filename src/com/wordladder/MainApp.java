package com.wordladder;

import com.wordladder.generator.GraphGenerator;
import com.wordladder.model.SearchResult;
import com.wordladder.model.WordGraph;
import com.wordladder.handler.BfsHandler;
import com.wordladder.handler.DfsHandler;
import com.wordladder.handler.LadderHandler;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        String dictionaryFile = "words.txt";
        String templateOutputFile = "result-graph.txt";
        int wordLength = 4;

        try {
            // generating the graph from words.txt
            long generateStart = System.nanoTime();
            GraphGenerator generator = new GraphGenerator();
            WordGraph graph = generator.generateFromFile(dictionaryFile, wordLength);
            long generateEnd = System.nanoTime();

            double generateTimeMs = (generateEnd - generateStart) / 1000000.0;
            System.out.printf("Graph generated in %.2f ms%n", generateTimeMs);

            // Exporting 50-word template to text file
            graph.exportTemplateToFile(templateOutputFile, 50);
            System.out.println("Saved 50-word template graph to " + templateOutputFile);

            // Creating the BFS and DFS handlers
            LadderHandler bfs = new BfsHandler();
            LadderHandler dfs = new DfsHandler();

            // Testing multiple start and end word pairs
            String[][] testPairs = {
                {"FOOL", "SAGE"},
                {"COLD", "WARM"},
                {"HEAD", "TAIL"}
            };

            for (int i = 0; i < testPairs.length; i++) {
                String startWord = testPairs[i][0];
                String endWord = testPairs[i][1];

                System.out.println("\n========================================");
                System.out.println("WORD LADDER: " + startWord + " >> " + endWord);
                System.out.println("========================================");

                // Run and print BFS
                SearchResult bfsResult = bfs.solve(graph, startWord, endWord);
                printResult(bfsResult);

                // Run and print DFS
                SearchResult dfsResult = dfs.solve(graph, startWord, endWord);
                printResult(dfsResult);
            }

        } catch (Exception e) {
            System.out.println("Error: Could not read " + dictionaryFile);
            System.out.println("Put words.txt is inside the project root folder!");
            e.printStackTrace();
        }
    }

    // Helper method for printing the ladder and timing neatly
    private static void printResult(SearchResult result) {
        System.out.printf("[%s] Running Time: %.4f ms | Total Steps: %d%n",
                result.getAlgorithmName(),
                result.getTimeInMilliseconds(),
                result.getSteps());

        List<String> path = result.getPath();

        if (path.isEmpty()) {
            System.out.println("No path found.\n");
        } else if (path.size() > 25) {
            // shortening the printout in case DFS is too large so it fits on screen
            System.out.print("Ladder (first 10 and last 5 of " + path.size() + " words): ");
            for (int i = 0; i < 10; i++) {
                System.out.print(path.get(i) + " >> ");
            }
            System.out.print("... >> ");
            for (int i = path.size() - 5; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) {
                    System.out.print(" >> ");
                }
            }
            System.out.println("\n");
        } else {
            // Printing the full ladder
            System.out.println("Ladder: " + String.join(" >> ", path) + "\n");
        }
    }
}
