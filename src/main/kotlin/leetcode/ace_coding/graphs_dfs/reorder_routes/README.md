# 1466. Reorder Routes to Make All Paths Lead to the City Zero

There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

```text
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 2:
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
```

Constraints:

- 2 <= n <= 5 * 10^4
- connections.length == n - 1
- connections[i].length == 2
- 0 <= ai, bi <= n - 1
- ai != bi

## Solution

Because we need to bring everyone to node 0, we can model the graph as a tree rooted at node 0 (the problem statement hints at this by stating that the network forms a tree structure). We can imagine that in order to move from any node to the root, all edges must be directed from a child to its parent. If there is an edge from a parent node to its child node, no node in the subtree of the child can reach the root node. This edge must be flipped.

So, our task is to count the number of edges in a tree rooted at node '0' that are directed from the parent node to a child node.

We must traverse the entire tree to determine the number of such edges that are directed from the parent to the child node. To traverse the tree, we can use a graph traversal algorithm such as depth-first search (DFS).

In DFS, we use a recursive function to explore nodes as far as possible along each branch. Upon reaching the end of a branch, we backtrack to the previous node and continue exploring the next branches.

Once we encounter an unvisited node, we will take one of its neighbor nodes (if exists) as the next node on this branch. Recursively call the function to take the next node as the 'starting node' and solve the subproblem.

The caveat is that our edges are directed. To count the number of edges that are directed from a parent to its child node, we must traverse the entire tree. If there is an edge from a child to its parent node, we will be unable to reach the child from the parent.

To traverse the entire tree, we must find a way to get from node 0 to all of the nodes in any case. This is possible if the edges are treated as undirected. We add an opposite edge from node b to node a for every given edge in connections from node a to node b. Let us refer to the edge we added as an "artificial" edge and the edge present in connections as an "original" edge.

If we use an "artificial" edge to move from the parent node to the child node, we know that the original edge is directed from the child node to the parent node. We don't need to flip the "original" edge.

If we use an "original" edge to move from the parent node to the child node, it means we need to flip this edge. Whenever we encounter such an edge, we will increment our answer variable by 1.

We can distinguish between an "original" and an "artificial" edge in many different ways (assigning booleans, specific numbers, etc.). In this article, we will associate an extra value with each edge - 1 for "original" edges and 0 for "artificial" edges.

We also set an answer variable count = 0 to count the number of edges that must be flipped. Now we start a DFS from node 0 and work our way down the tree (from parent to child). If we come across an "original" edge during the traversal, that is, an edge labeled with a 1, we increase the count by one. We don't modify count if we come across an "artificial" edge. We can combine these two operations and perform count += sign where sign is either 0 or 1 indicating an "artificial" or "original" edge.

We have our answer in count at the end of the traversal.

Algorithm

- Create an integer variable count to count the number of edges that must be flipped. We initialize it with 0.
- Create an adjacency list adj that contains a list of pairs of integers such that adj[node] contains all the neighbors of node in the form of (neighbor, sign) where neighbor is the neighboring node of node and sign denotes the direction of the edge i.e., whether its an "original" or "artificial" edge.
- Start a DFS traversal.
  - We use a function dfs to perform the traversal. For each call, pass node, parent, adj as the parameters. We start with node 0 and parent as -1.
  - Iterate over all the children of the node (nodes that share an edge) using adj[node]. For every child, sign in adj[node], check if child is equal to parent. If child is equal to parent, we will not visit it again.
  - If child is not equal to parent, we perform count += sign and recursively call the dfs with node = child and parent = node. At the end of the dfs traversal, we have the total edges that are required to be flipped in count.
- Return count.
