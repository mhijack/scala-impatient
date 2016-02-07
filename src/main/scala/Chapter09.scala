import java.io.{File, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}

object Chapter09 {

  /**
   * Task 1:
   *
   * Write a Scala code snippet that reverses the lines in a file
   * (making the last line the first one, and so on).
   */
  def reverseLines(file: File): Unit = {
    val source: BufferedSource = Source.fromFile(file)
    val lines = try {
      source.getLines().toBuffer.reverse
    }
    finally {
      source.close()
    }

    val writer = new PrintWriter(file)
    try {
      lines.foreach(writer.println)
    }
    finally {
      writer.close()
    }
  }

  /**
   * Task 2:
   *
   * Write a Scala program that reads a file with tabs, replaces each tab with spaces
   * so that tab stops are at n-column boundaries, and writes the result to the same file.
   */
  def replaceTabs(file: File, charsPerColumn: Int = 4): Unit = {
    var chars = 0
    val buff = new ArrayBuffer[Char]
    val source: BufferedSource = Source.fromFile(file)
    try for (c <- source) c match {
      case '\t' => for (_ <- 0 until (charsPerColumn - chars % charsPerColumn)) buff += ' '
        chars = 0
      case '\n' => buff += c
        chars = 0
      case _ => buff += c
        chars += 1
    }
    finally {
      source.close()
    }

    val writer = new PrintWriter(file)
    try {
      buff.foreach(writer.print)
    }
    finally {
      writer.close()
    }
  }
}
