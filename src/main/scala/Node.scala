package main.scala

/**
  * Class which represents node in the graph
  * Method add used to build references between nodes.
  */


class Node(val n: Int) {
  var refs: List[Int] = List()
  var graphId = 0

  def add(x: Int) = {
    this.refs = x :: refs
  }

  def apply(n: Int): Node = {
    var id = n
    new Node(n)
  }

  override def toString = s"Node(id = $n, graphId = $graphId, references = $refs)"
}
