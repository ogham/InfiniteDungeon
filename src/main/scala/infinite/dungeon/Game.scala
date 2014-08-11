package infinite.dungeon

import java.io.{BufferedReader, PrintStream}

import infinite.dungeon.action.Action
import infinite.dungeon.room.{Dungeon, Room}

import scala.util.Random

/**
 * Main program entry point.
 */
class Game(in: BufferedReader, out: PrintStream) {
  var currentRoom: Room = new Dungeon()
  val level = Level.build(currentRoom, new Random())
  var playing = true

  def readChoice(): Int = {
    while (true) {
      out.print("\n> ")
      var input = in.readLine()
      if (input == null || input == "quit") {
        playing = false
        return -1
      }
      if (input.forall(_.isDigit)) {
        return input.toInt
      }
    }
    -1
  }

  def play() {
    while (playing) {
      out.println(level.describeRoom(currentRoom))
      out.println("")
      val actions: Seq[Action] = level.actionsForRoom(currentRoom)
      actions.zipWithIndex.foreach(m => out.println(m._2 + ") " + m._1.describe()))

      val choice = readChoice()
      if (choice >= 0) {
        actions(choice).perform(this)
      }
    }
  }

  def println(s: String) {
    out.println(s)
  }
}

