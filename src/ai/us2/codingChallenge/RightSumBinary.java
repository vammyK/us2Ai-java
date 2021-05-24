package ai.us2.codingChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RightSumBinary {


    /*
    Third try the best try :-D
    Theory: Although the class says Binary, Its not exactly Binary.
            But instead of doing even DFS or BFS, the idea is, To find a median path. For a 4*4 Matrix it will be RDRDRD
            And this median Path will give the Middle Value.
            Post this depending on sum, if Sum<Middle, We try to bring the PATH to either all R's and all D's like RRRDDD ---> This will give the least value
            OR if sum>Middle, we try to Bring PATH to all D's and R's this is the Maximum possible value.

            The way to do it is either to Flip R with D depending on Condition A or Condition B
            And the moment we hit either MIN or MAX, we abort the code, as thats the limit

     Algorithm:
     1. Calculate Median Path
     2. Calculate Sum of Median path
     3. A. IF SUM<FINALSUM
            KEEP FLIPPING D with R
        B. IF SUM>FINALSUM
            KEEP FLIPPING R with D
        C. IF SUM keeps Repeating, BREAK.
     4. RETURN the modified PATH.

     */
    private String rightSum(int m, int n, int finalSum) {
        List<String> medianPath = generateMedianPath(m, n);
        int sum = calculateSum(medianPath);
        int prevSum = 0;
        while (sum != finalSum) {
            if (sum < finalSum) {
                // Flip D with R
                medianPath = flipDR(medianPath);

            }
            if (sum > finalSum) {
                medianPath = flipRD(medianPath);
            }
            sum = calculateSum(medianPath);
            if (prevSum == sum) {
                System.out.println("Impossible Answer ! Abort");
                medianPath = new ArrayList<>();
                break;
            }
            prevSum = sum;
        }

        return medianPath.stream().collect(Collectors.joining());
    }

    private List<String> flipRD(List<String> medianPath) {
        for (int i = 2; i < medianPath.size(); i++) {
            if (medianPath.get(i - 1) == "D" && medianPath.get(i) == "R") {
                medianPath.set(i - 1, "R");
                medianPath.set(i, "D");
                break;
            }
        }
        return medianPath;
    }

    private List<String> flipDR(List<String> medianPath) {
        for (int i = 1; i < medianPath.size(); i++) {
            if (medianPath.get(i - 1) == "R" && medianPath.get(i) == "D") {
                medianPath.set(i - 1, "D");
                medianPath.set(i, "R");
                break;
            }
        }
        return medianPath;
    }

    private void flip() {
    }

    private int calculateSum(List<String> path) {
        int sum = 1;
        int number = 1;
        for (String s : path) {
            if (s == "R") {
                sum += number;
            } else {
                number++;
                sum += number;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        RightSumBinary rightSumBinary = new RightSumBinary();
        String ans = rightSumBinary.rightSum(9, 1000, 2831);
        System.out.println(ans);

    }
    /*
    For EACH M &N, We need to go Right M-1 times and Down N-1 Times.
    Divide M with N, and that many times a switch will keep Happening.
     */
    List<String> generateMedianPath(Integer m, Integer n) {
        List<String> ret = new ArrayList<>();
        int mTemp = m - 1;
        int nTemp = n - 1;
        int flipNum = Math.max(Math.floorDiv(m, n), 1);
        while (mTemp > 0 || nTemp > 0) {
            for (int i = 0; i < flipNum; i++) {
                if (nTemp > 0) {
                    ret.add("R");
                }
                nTemp--;
            }
            if (mTemp > 0) {
                ret.add("D");
            }
            mTemp--;

        }
        return ret;
    }

}
