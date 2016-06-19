package main.scala

/**
  * Class which represents Graph
  * Currently hasn't been used.
  *
  */


class Graph {
  private var nodes: Set[Node] = Set()

  def add(n: Node) = {
    nodes += n
  }

  override def toString = s"Graph($nodes)"
}

