# **Knight's Tour with Heuristics**

## **Overview**

This repository contains the implementation of various search algorithms to solve the **Knight's Tour Problem (KTP)**, a classic computational problem in chess. The implemented algorithms include:

- **Breadth-First Search (BFS)**
- **Depth-First Search (DFS)**
- **DFS with Heuristic h1b (Warnsdorff's Rule)**
- **DFS with Heuristic h2 (Enhanced Warnsdorff's Rule)**

The project explores the efficiency of these algorithms in finding complete tours on chessboards of different sizes.

---

## **Features**

- Solves the Knight's Tour Problem for **n x n chessboards**.
- Supports various search strategies with time-limited execution.
- Outputs detailed results, including:
  - Search method used and status message.
  - Solution (if found) or failure reasons (e.g., timeout, out of memory).
  - Time taken and number of nodes expanded.

---

## **How to Use**

### **Prerequisites**

- Install a compatible compiler for Java (`javac`).
- Clone this repository:
  ```bash
  git clone https://github.com/yigit-ak/Knights-Tour-with-Heuristics.git
  cd Knights-Tour-with-Heuristics
  ```

### **Usage**

1. **Compile the Program**:

   ```bash
   javac -d bin src/**/*.java
   ```

2. **Run the Program**:

   ```bash
   java -cp bin Main
   ```

3. **Input Parameters**:
   - Board size `n` (e.g., 8, 16, 32).
   - Search method:
     - `a` for BFS
     - `b` for DFS
     - `c` for DFS + h1b
     - `d` for DFS + h2
   - Time limit `t` (in seconds).

---

## **Example**

For a 16x16 chessboard using DFS with Heuristic h2 and a 15-minute limit:

```bash
Input:
Board size: 16
Search method: d
Time limit: 900

Output:
Search Method: DFS with Heuristic h2
Time Limit: 900 seconds
Status: Solution Found
Solution: [List of knight's moves]
Time Spent: 514000 milliseconds
Nodes Expanded: 15,892
```

---

## **Project Structure**

```
KnightsTour-SearchAlgorithms/
├── data/                        # Sample test cases and results
├── docs/                        # Design document and analysis report
├── src/
│   ├── problem_definition/      # Problem definition classes
│   │   ├── State.java
│   │   ├── Location.java
│   ├── search_algorithms/       # Search algorithm implementations
│   │   ├── SearchAlgorithm.java # Abstract base class for search algorithms
│   │   ├── BreadthFirstSearch.java
│   │   ├── DepthFirstSearch.java
│   │   ├── DfsWithHeuristicH1B.java
│   │   ├── DfsWithHeuristicH2.java
│   ├── Main.java                # Main class to run the program
│   ├── ExperimentConfig.java    # Configuration class for experiments
├── .gitignore                   # Git ignored files
├── README.md                    # Project documentation
```

---

## **Results**

Detailed experimental results for board sizes 8, 16, 32, 41, and 52 are available in the `docs/` folder. Highlights:

- BFS works efficiently for small boards but becomes infeasible as `n` grows.
- Heuristic h2 demonstrates superior performance, solving large boards within time limits.

---

## **References**

- Luis Paris, _Heuristic Strategies for the Knight's Tour Problem (2004)_.

---

## **Contributors**

- Yiğit Mesut Ak
- Beyza Lekesiz
- Emir Büçkün

---

Feel free to adapt this as needed based on the structure of your repository!
