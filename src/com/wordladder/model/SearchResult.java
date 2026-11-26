package com.wordladder.model;

import java.util.List;

public class SearchResult {

    private String algoName;
    private List<String> path;
    private long timeInNanoseconds;

    public SearchResult(String algoName, List<String> path, long timeInNanoseconds) {
        this.algoName = algoName;
        this.path = path;
        this.timeInNanoseconds = timeInNanoseconds;
    }

    public String getAlgorithmName() {
        return algoName;
    }

    public List<String> getPath() {
        return path;
    }

    // Number of word changes is total words - 1
    public int getSteps() {
        if (path.isEmpty()) {
            return 0;
        }
        return path.size() - 1;
    }

    // Converting nanoseconds to milliseconds for smooth reading
    public double getTimeInMilliseconds() {
        return timeInNanoseconds / 1000000.0;
    }
}
