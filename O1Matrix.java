import java.util.*;
import java.util.LinkedList;

// Time Complexity : O(m * n)  
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Intuition: use a queue to traverse the grid in a BFS manner, marking cells with 1 as distances from the nearest 0

//bfs
public class O1Matrix {

    // Directions for neighbors (up, down, left, right)
    int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int depth = 1;

        // Step 1: Add all 0's to queue and mark them as visited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = -1;
                } else {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        if (queue.isEmpty())
            return mat;

        // Step 2: BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();

                for (int[] dir : dirs) {
                    int newRow = cell[0] + dir[0];
                    int newCol = cell[1] + dir[1];

                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && mat[newRow][newCol] == -1
                            && mat[newRow][newCol] != 0) {
                        mat[newRow][newCol] = depth;
                        queue.offer(new int[] { newRow, newCol });
                    }
                }
            }
            depth++;
        }

        return mat;
    }
}

// bfs using parent
class BFSusingParentCell {

    // Directions for neighbors (up, down, left, right)
    int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        // int depth = 1;

        // Step 1: Add all 0's to queue and mark them as visited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = -1;
                } else {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        // Step 2: BFS
        while (!queue.isEmpty()) {
            // int size = queue.size();
            // for(int i=0; i < size; i++){ //size not needed
            int[] cell = queue.poll();

            for (int[] dir : dirs) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];
                int parent = mat[cell[0]][cell[1]]; // how we store parent cell

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && mat[newRow][newCol] == -1
                        && mat[newRow][newCol] != 0) {
                    mat[newRow][newCol] = parent + 1;
                    queue.offer(new int[] { newRow, newCol });
                }
            }
            // }
            // depth++;
        }

        return mat;
    }
}
