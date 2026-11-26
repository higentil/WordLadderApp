package com.wordladder.handler;

import com.wordladder.model.SearchResult;
import com.wordladder.model.WordGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DfsHandler extends LadderHandler {

    @Override
    public SearchResult solve(WordGraph graph, String startWord, String endWord) {
        long startTime = System.nanoTime();

        String start = startWord.toUpperCase();
        String end = endWord.toUpperCase();

        // Checking if both words exist in the dictionary
        if (!graph.containsWord(start) || !graph.containsWord(end)) {
            long timeSpent = System.nanoTime() - startTime;
            return new SearchResult("DFS", new ArrayList<>(), timeSpent);
        }

        // Implementing LIFO for DFS
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        // Pushing the first word onto the stack
        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            // Popping out the most recently added word from the top of the stack
            String currentWord = stack.pop();

            // Checking if the targeted word is reached
            if (currentWord.equals(end)) {
                List<String> path = generateLadderPath(parentMap, start, end);
                long timeSpent = System.nanoTime() - startTime;
                return new SearchResult("DFS", path, timeSpent);
            }

            // Checking all neighboring words
            List<String> neighbors = graph.getNeighbors(currentWord);
            for (int i = 0; i < neighbors.size(); i++) {
                String neighbor = neighbors.get(i);

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentWord);
                    stack.push(neighbor);
                }
            }
        }

        // Returning an empty list if no path exists
        long timeSpent = System.nanoTime() - startTime;
        return new SearchResult("DFS", new ArrayList<>(), timeSpent);
    }
}
