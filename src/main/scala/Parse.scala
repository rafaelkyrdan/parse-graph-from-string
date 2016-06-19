package main.scala

import scala.collection.mutable.{Map => MMap}

/**
  * The task described in the README.md
  *
  */

object Parse {

  def main(args: Array[String]) {

    val ls = parseString("1-2,1-4,2-4,4-5,5-6,6-1,8-9,10")
    val nodes = parseNodes(ls)
    val c = count(nodes)

    println(c)
  }


  /**
    * Parse string on pair of connected nodes
    *
    * @param s , string like this "1-2,1-4,2-4,4-5,5-6,6-1,8-9,10"
    * @return array like this [ [1,2], [1,4], [4,5], [5,6], [8-9], [10] ]
    */

  def parseString(s: String) = {

    //doesn't match requirements because we lost references between nodes.
    //s.split(",").flatMap({ case (x: String) => x.split("-") })

    s.split(",").map({ case (x: String) => x.split("-") })

  }

  /**
    * Parse nodes and their references between each other.
    *
    * @param ls is array like this [ [1,2], [1,4], [4,5], [5,6], [8-9], [10] ]
    * @return a map with nodes, where each node looks like this:
    *         Node(id = 1, references = List(4, 2)),
    *         Node(id = 4, references = List(5))...
    */

  def parseNodes(ls: Array[Array[String]]): MMap[Int, Node] = {

    def getOrCreate(m: MMap[Int, Node], id:Int):Node = {
      m.getOrElse(id, new Node(id))
    }

    @annotation.tailrec
    def go(ls: List[Array[String]], m: MMap[Int, Node]): MMap[Int, Node] = {
      ls match {
        case h :: t => {
          val l = h.toList
          //first step
          //we check wether map already includes this node
          //if not we create and put it in the map
          //we use Node's id as a key in the map
          val n = getOrCreate(m, l.head.toInt)

          //second step
          //if new node has references to other nodes
          //then we build these nodes as well
          //and add their id's to @property `refs`
          if (l.size > 1) {
            val n1 = getOrCreate(m, l.last.toInt)
            n.add(l.last.toInt)
            m.+=(l.last.toInt -> n1)
          }

          //third step we put node in the map with updated `refs`
          m.+=(l.head.toInt -> n)
          go(t, m)
        }
        case Nil => m
      }
    }

    go(ls.toList, MMap[Int, Node]())
  }


  /**
    * Function calculate how many graph is in the sequence of node,
    * the most challenging part of the function is criteria to stop looping,
    * because some graph can be closed on itself.
    *
    * @param m :MMap[Int, Node]
    * @return int
    */

  def count(m: MMap[Int, Node]): Int = {

    var count = 0
    var marked = 0
    val keys = m.keySet.toList
    val iter = m.iterator


    /**
      * TODO: Rewrite this function as pure function
      *
      */

    def mark(id: Int, graphId: Int): Unit = {
      m.get(id) match {
        case None => // never happened, so just remove
        case Some(x) =>
          if (x.graphId == 0) {
            x.graphId = graphId
            marked += 1
            x.refs match {
              case Nil => //println("empty2")
              case x1 :: _ => x.refs.foreach((y: Int) => mark(y, graphId))
            }
          }
      }
    }

    //criteria to stop looping
    while (keys.size > marked) {
      val p = iter.next()
      if (p._2.graphId == 0) {
        count = count + 1
        mark(p._1, count)
      }
    }

    //uncomment to see the nodes
//    m.foreach(x => {
//      println(x._2)
//    })

    count
  }

}