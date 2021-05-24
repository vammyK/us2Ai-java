package ai.us2.codingChallenge;

import java.util.Stack;

public class RightSum2 {


    private static String result = null;
    Stack<Metadata> metaStack = new Stack<>();

    /*
    Try to optimise the RightSum1 by implementing DFS instead of using Recursion --> To avoid redundant calls and stack overflows for large indices

    Here use a Stack to keep the Left out nodes while we perform DFS while always going Right by default.
    Problem: Still this takes inderminate time for large cases, need to fix it.
     */
    public String rightSum(Integer m, Integer n, Integer sum) {
        int[][] data = getPopulatedArray(m, n);
        Metadata meta = new Metadata(0, 0, "", 0);
        metaStack.push(meta);
        findPaths(data, sum);
        System.out.println(result);
        return result;
    }

    private void findPaths(int[][] data, int finalSum) {
        while (!metaStack.isEmpty()) {
            Metadata meta = metaStack.pop();
            dfs(meta,data,finalSum);
        }


    }

    private void dfs(Metadata meta, int[][] data, int finalSum) {

        if (meta.i > data.length - 1 || meta.j > data[0].length - 1) {
            return;
        } else if ((meta.i + 1) == data.length && (meta.j + 1) == data[meta.i].length) {
            if (finalSum == meta.sum + data[meta.i][meta.j]) {
                result = meta.path;
                return;
            }
        } else {
            int currentSum= meta.sum;
            currentSum += data[meta.i][meta.j];
            if (currentSum >= finalSum) {
                return;
            }else {
                Metadata right= new Metadata(meta.i,meta.j+1,meta.path+"R",currentSum);
                Metadata down= new Metadata(meta.i+1,meta.j,meta.path+"D",currentSum);
                metaStack.push(down);
                dfs(right,data,finalSum);
            }
        }

    }

    private int[][] getPopulatedArray(Integer m, Integer n) {
        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = i + 1;
            }
        }
        return ret;
    }


    class Metadata {
        int i;
        int j;
        String path;
        int sum;

        public Metadata(int i, int j, String path, int sum) {
            this.i = i;
            this.j = j;
            this.path = path;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        RightSum2 rightSum = new RightSum2();

        String result = rightSum.rightSum(9, 1000, 2831);

    }



}
