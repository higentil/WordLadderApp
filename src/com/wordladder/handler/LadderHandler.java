package com.wordladder.handler;

import com.wordladder.model.SearchResult;
import com.wordladder.model.WordGraph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class LadderHandler {

    // Default handler method for BFS and DFS
    public abstract SearchResult solve(WordGraph graph, String startWord, String endWord);

    // Tracing backwards from endWord to startWord using the parentMap
    protected List<String> generateLadderPath(Map<String, String> parentMap, String startWord, String endWord) {
        List<String> ladder = new ArrayList<>();
        String currentWord = endWord;

        // Walking backwards from the end word to the start word
        while (currentWord != null) {
            ladder.add(currentWord);
            if (currentWord.equals(startWord)) {
                break;
            }
            currentWord = parentMap.get(currentWord);
        }

        // Reversing the list so that it goes from startWord >> endWord
        Collections.reverse(ladder);
        return ladder;
    }
}
