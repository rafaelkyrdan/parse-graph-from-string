package main.scala

/**
  * Class which represents Graph
  * Currently hasn't been used.
  *
  */


class Graph {
  var nodes: Set[Node] = Set()

  def apply: Graph = new Graph()

  def add(n: Node) = {
    this.nodes += n
  }

  override def toString = s"Graph($nodes)"
}
