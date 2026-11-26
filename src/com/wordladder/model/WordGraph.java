package com.wordladder.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordGraph {

    // Storing each word and a list of all words that differ by 1 letter
    private Map<String, List<String>> nearList = new HashMap<>();

    // Adding a word to the graph
    public void addWord(String word) {
        if (!nearList.containsKey(word)) {
            nearList.put(word, new ArrayList<>());
        }
    }

    // Connecting two words together in both directions
    public void addBorder(String word1, String word2) {
        addWord(word1);
        addWord(word2);

        List<String> neighbors1 = nearList.get(word1);
        if (!neighbors1.contains(word2)) {
            neighbors1.add(word2);
        }

        List<String> neighbors2 = nearList.get(word2);
        if (!neighbors2.contains(word1)) {
            neighbors2.add(word1);
        }
    }

    // Checking if a word exists in the graph
    public boolean containsWord(String word) {
        return nearList.containsKey(word);
    }

    // Returning all connected words for a given word
    public List<String> getNeighbors(String word) {
        if (nearList.containsKey(word)) {
            return nearList.get(word);
        }
        return new ArrayList<>(); 
    }

    // Writing the first 50 words and their neighbors to a text file
    public void exportTemplateToFile(String fileName, int limit) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        writer.println("=== WORD LADDER GRAPH TEMPLATE (First " + limit + " Words) ===");
        writer.println();

        int count = 0;
        for (String word : nearList.keySet()) {
            if (count >= limit) {
                break;
            }
            List<String> neighbors = nearList.get(word);
            writer.println(word + " -> " + neighbors);
            count++;
        }

        writer.close();
    }
}
