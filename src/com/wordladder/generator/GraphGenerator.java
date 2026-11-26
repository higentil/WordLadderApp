package com.wordladder.generator;

import com.wordladder.model.WordGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphGenerator {

    // Reading words from the words file and generating the graph
    public WordGraph generateFromFile(String fileName, int targetLength) throws FileNotFoundException {
        List<String> validWords = new ArrayList<>();
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        // Reading every line from the words text file
        while (scan.hasNextLine()) {
            String word = scan.nextLine().trim().toUpperCase();

            // Keeping only words of the given exact length
            if (word.length() == targetLength && !validWords.contains(word)) {
                validWords.add(word);
            }
        }
        scan.close();

        System.out.println("Loaded " + validWords.size() + " words of length " + targetLength + ".");
        return buildGraph(validWords);
    }

    // Connecting two words that differ by only 1 letter
    private WordGraph buildGraph(List<String> words) {
        WordGraph graph = new WordGraph();

        // Adding all words to the graph first
        for (int i = 0; i < words.size(); i++) {
            graph.addWord(words.get(i));
        }

        // Comparing every pair of words
        for (int i = 0; i < words.size(); i++) {
            String word1 = words.get(i);

            for (int j = i + 1; j < words.size(); j++) {
                String word2 = words.get(j);

                if (isOneLetterApart(word1, word2)) {
                    graph.addBorder(word1, word2);
                }
            }
        }

        return graph;
    }

    // Helper method for counting how many letters are different between two words
    private boolean isOneLetterApart(String word1, String word2) {
        int apartNum = 0;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                apartNum++;
            }
            
            if (apartNum > 1) {
                return false;
            }
        }

        return apartNum == 1;
    }
}
