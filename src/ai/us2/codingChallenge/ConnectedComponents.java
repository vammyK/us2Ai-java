package ai.us2.codingChallenge;

import java.util.Arrays;

public class ConnectedComponents {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {0, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 1}
        };

        ConnectedComponents cc = new ConnectedComponents();
        System.out.println(Arrays.deepToString(cc.cc4Connectivity(data)));

    }

    static int[][] connectivity;
    static int graphs = 0;

    /*
    Explanation:
    The connected components of an 2d Array/pixels can be considered as a Graph and to traverse it,
    we use BFS, and traverse all connected components, once a given BFS finishes, change the number to find 2nd graph and so on.
     */
    int[][] cc4Connectivity(int[][] pixels) {
        connectivity = generateBlank2DArray(pixels.length, pixels[0].length);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] != 0 && connectivity[i][j] == 0) {
                    graphs++;
                }
                bfs(pixels, graphs, i, j);

            }
        }
        return connectivity;

    }

    private void bfs(int[][] pixels, int graphs, int i, int j) {
        if (i > pixels.length - 1 || j > pixels[0].length - 1 || i < 0 || j < 0) {
            return;
        }
        if (connectivity[i][j] > 0) {
            return;
        }
        if (pixels[i][j] == 0) {
            return;
        }
        connectivity[i][j] = graphs;

        // up
        bfs(pixels, graphs, i - 1, j);
        //down
        bfs(pixels, graphs, i + 1, j);
        //left
        bfs(pixels, graphs, i, j - 1);
        //right
        bfs(pixels, graphs, i, j + 1);

    }


    private int[][] generateBlank2DArray(int x, int y) {
        int[][] ret = new int[x][y];
        for (int i = 0; i < x; i++) {
            int[] temp = new int[y];
            Arrays.fill(temp, 0);
            ret[i] = temp;
        }
        return ret;
    }

}
