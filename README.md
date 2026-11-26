# Word Ladder Solver App

This Java program solves the **Word Ladder** puzzle: transforming a start word into an end word by changing **one letter at a time**, where every step must be a real word.

## How It Works

1. **Creates a Word Map (`GraphGenerator` & `WordGraph`)**
   The program reads a dictionary of words from `words.txt` and connects any two words that differ by just one letter (for example, `FOOL` connects to `POOL`, and `POOL` connects to `POLL`). It also saves the first 50 words and their connections into `result-graph.txt`.

2. **Finds the Shortest Path with BFS (`BfsHandler`)**
   **Breadth-First Search** checks all words 1 letter apart first, then 2 letters apart, expanding outward level by level using a **Queue**. Because it checks closest words first, BFS always finds the **shortest possible ladder**.

3. **Explores Deep Paths with DFS (`DfszHandler`)**
   **Depth-First Search** picks one neighboring word and follows that single path as deep as it can using a **Stack**, backtracking only when it hits a dead end. It finds a valid ladder, though usually with many more steps than BFS.

4. **Compares Results (`Main`)**
   Both algorithms track visited words to avoid going in circles and leave trail (`parentMap`) to trace the final ladder back from end to start. The program prints both ladders and compares how fast each algorithm ran.

