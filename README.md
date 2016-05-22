# parse-graph-from-string
Project for self-education.

## What's it about?
It is algorithmic exercise on parsing string.
Input is a string which represents list of graph.
The graph is an abstract data type, consists of a finite set of nodes.
In this example we assume that these graph are directed graph, like on picture.
Example of the input string: `1-2,1-4,2-4,4-5,5-6,6-1,8-9,10`.
Example of the graph: 

![image](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Directed.svg/125px-Directed.svg.png)

Task: calculate how many graph is in the string?
So first we parse the string, second step we build nodes and their references between each other.
Last step we go through the sequence and calculate how many graph is in the sequence.
The most challenging part of the function `count` is criteria to stop looping,
function `count` mark each node in order to stop looping.
So function count should return the number of graph.

Challenges:
- some graph consist from one node
- some nodes connected to many other nodes
- some graph closed on itself
- find criteria to stop looping

## Todo:
1. try to re-write count method with pure functions
2. try to find better criteria to stop looping
3. try to use Graph entity
