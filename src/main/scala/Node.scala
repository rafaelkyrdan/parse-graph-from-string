package main.scala

import com.sun.javaws.security.AppPolicy

/**
  * Class which represents node in the graph
  * Method add used to build references between nodes.
  */


class Node(private val n: Int) {

  //leave these public for now because we need access them:
  //we need access to @variable refs to traverse children in the `count` function
  //we need access to @variable graphId because at the moment instantiation
  // of the object we don't know parent's id
  var refs: List[Int] = List()
  var graphId = 0

  def add(x: Int) = {
    refs = x :: refs
  }

  override def toString = s"Node(id = $n, graphId = $graphId, references = $refs)"
}