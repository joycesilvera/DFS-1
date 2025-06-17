import java.util.*;
import java.util.LinkedList;

// dfs
// Time Complexity : O(m * n)
// Space Complexity : O(m * n) for recursion stack 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Intuition: Use DFS to traverse the image starting from the pixel (sr, sc) 
//and change the color of all connected pixels with the same original color 
//to the new color.
public class FloodFill {
    int m;
    int n;
    int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        m = image.length;
        n = image[0].length;
        int original = image[sr][sc];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if the pixel is the starting pixel, call dfs
                if (i == sr && j == sc) {
                    dfs(image, i, j, color, original);
                }
            }
        }
        return image;
    }

    public void dfs(int[][] image, int i, int j, int newColor, int original) {
        if (i < 0 || j < 0 || i == m || j == n || image[i][j] == newColor || image[i][j] != original)
            return;
        // change the color of the pixel
        image[i][j] = newColor;

        for (int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            // call dfs for all neighbor cells
            dfs(image, nr, nc, newColor, original);
        }

    }
}

// bfs
// Time Complexity : O(m * n)
// Space Complexity : O(m * n) for queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Intuition: Use BFS to traverse the image starting from the pixel (sr, sc)
class Solution {
    int m;
    int n;
    int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        m = image.length;
        n = image[0].length;
        int original = image[sr][sc];
        Queue<int[]> queue = new LinkedList<>();

        // color the start cell
        image[sr][sc] = color;
        queue.offer(new int[] { sr, sc });

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];

                // color all neighbor cells with the same original color
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && image[nr][nc] != color && image[nr][nc] == original) {
                    image[nr][nc] = color;
                    queue.offer(new int[] { nr, nc });

                }
            }
        }

        return image;
    }
}