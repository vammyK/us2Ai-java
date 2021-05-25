# Coding Assignment -- US2.AI
## System Requirements

- OpenJdk 11
- Any IDE -- Intellij IDEA Preferred

## Running The Demo Cases
- Checkout code locally.
- Directly run main method of Demo.java
- If required, can run each class main method seperately.

## Assignments Solved


### Assignment 1 [Same Pair Count --SamePairCount.java]:
_Time Complexity_:   O(NLog(N)) where N= No. Of Possible Binary Sequences.
```text
Theory: Bottom Up Dynamic Programming
Used a building block, 00,01,10,11 as known Binary Sequences to iterate and build the 10 Bit binary sequences.
Used HashMap to avoid duplicate/redundant conversions.
```

### Assignment 2 [Operations for Right Sum -- RightSumBinary.java]:
_Time Complexity_: O(N)
```text
Theory: A form of Binary Search
For a given Matrix, Find the Median/Diagonal Path with constraint of going either Right or Down Only.
Such path P will reflect the Middle Score of the numbers, For any sum greater then middle score, the path will explicitly be below the diagonal, and for any sum lesser than middle score will be above the diagonal path.

After finding the Median path, Depending on condition, swap the steps and find the answer. 
```


### Assignment 3 [Connected Components of an Image -- ConnectedComponents.java]:
_Time Complexity:_ O(KV^2) where V--> Max Connected Components, K -- No. of different components
```text
Theory: A Form of Breadth First Search -- Solved using 4 connectivity
Each Pixel with a neighbour can be visualised as a Graph. And we can use BFS or DFS to traverse through the whole graph. 
This will provide one unique connected component. Then iterate over the Pixels Array, to find untraversed pixels and re-initiate a BFS to find different connected components
```


### Assignment 4 [Points Inside/Outside Polygon -- InsidePolygon.java]:
_Time Complexity: O(N) where N-> No. of Edges of polygon
```text
Theory 1: A Point can be inside a Polygon only if, A RAY drawn from the point towards Infinity intersects the Polyon ODD Number of times.
Theory 2: Given 2 Line Segments PQ and RS, the alignment of PQR will be Clockwise and PQS will be always Anti clockwise if they intersect each other. 

Making Use of Theory 1 and Theory 2, Create a Line Segment from Input Point to infinity, and check if it intersects Polygon odd number of times.
```
