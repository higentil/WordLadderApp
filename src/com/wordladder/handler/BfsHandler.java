package com.wordladder.handler;

import com.wordladder.model.SearchResult;
import com.wordladder.model.WordGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BfsHandler extends LadderHandler {

    @Override
    public SearchResult solve(WordGraph graph, String startWord, String endWord) {
        long startTime = System.nanoTime();

        String start = startWord.toUpperCase();
        String end = endWord.toUpperCase();

        // Checking if both words exist in the dictionary
        if (!graph.containsWord(start) || !graph.containsWord(end)) {
            long timeSpent = System.nanoTime() - startTime;
            return new SearchResult("BFS", new ArrayList<>(), timeSpent);
        }

        // Implementing FIFO for BFS
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        // Starting with the first word
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            // Removing the oldest word from the front of the queue
            String currentWord = queue.remove();

            // Checking if the targeted word is reached
            if (currentWord.equals(end)) {
                List<String> path = generateLadderPath(parentMap, start, end);
                long timeSpent = System.nanoTime() - startTime;
                return new SearchResult("BFS", path, timeSpent);
            }

            // Checking all neighboring words
            List<String> neighbors = graph.getNeighbors(currentWord);
            for (int i = 0; i < neighbors.size(); i++) {
                String neighbor = neighbors.get(i);

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentWord);
                    queue.add(neighbor);
                }
            }
        }

        // Returning an empty list if no path exists
        long timeSpent = System.nanoTime() - startTime;
        return new SearchResult("BFS", new ArrayList<>(), timeSpent);
    }
}
