package ai.us2.codingChallenge;

import java.util.List;

public class RightSum {

    public static void main(String[] args){
        RightSum rightSum= new RightSum();

        String result=rightSum.rightSum(9,1000,6113);

    }

    private static String result=null;

    /*
    Right Sum approach 1:
    Use Recursive Calls to Find the Path:

    Works well for small numbers but takes a lot of time for large indices. So tried to optimise using DFS in next solution.

    theory: The recursion Stops on following conditions
    1. If index has gone out of bounds
    2. If we have reached the desired sum and end of array at same time.
    3. If Sum has gone above expected/desired number.

    In CASE 2: Update the path
     */

    public String rightSum(Integer m, Integer n, Integer sum){

        int[][] data= getPopulatedArray(m,n);
        findPaths(data,0,0,0,sum,"");
        System.out.println(result);

        return result;
    }

    private void findPaths(int[][] data, int i, int j, int currentSum, Integer finalSum,String path) {
        if(i>data.length-1 || j>data[0].length-1){
            return;
        }

        if((i+1)==data.length && (j+1)== data[i].length){
                if(finalSum==currentSum+ data[i][j]) {
                    if(result==null || result.length()>path.length()){
                        result=path;
                    }

                }
             return;
        }

        currentSum+=data[i][j];
        if(currentSum>=finalSum){
            return;
        }
        findPaths(data,i,j+1,currentSum,finalSum,path+"R");
        findPaths(data,i+1,j,currentSum,finalSum,path+"D");

    }



    private int[][] getPopulatedArray(Integer m, Integer n) {
        int[][] ret= new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0;j<n;j++){
                ret[i][j]=i+1;
            }
        }
        return ret;
    }


}
