package infinite.dungeon

import java.io.{BufferedReader, InputStreamReader, PrintStream}

import infinite.dungeon.action.Action
import infinite.dungeon.room.{Dungeon, Room}

import scala.collection.mutable
import scala.util.Random

/**
 * Main program entry point.
 */
class Game(in: BufferedReader, out: PrintStream) {
  var currentRoom: Room = new Dungeon()
  var random = new Random()
  val level = Level.build(currentRoom, random)
  var playing = true

  def readChoice(choices: mutable.Map[Char, Action]): Option[Action] = {
    while (true) {
      out.print("\n> ")
      var input = in.readLine()
      if (input == null || input == "quit") {
        playing = false
        return None
      }
      else if (input.size == 1 && choices.contains(input.charAt(0))) {
        return Some(choices(input.charAt(0)))
      }
    }

    None
  }

  def play() {
    while (playing) {
      out.println(level.describeRoom(currentRoom))
      out.println("")
      val actions: mutable.Map[Char, Action] = level.actionsForRoom(currentRoom)
      val order = "NSWE0123456789"
      val sortedActions: Seq[(Char, Action)] = actions.toSeq.sortBy(a => order.indexOf(a._1))
      sortedActions.foreach(m => out.println(m._1 + ") " + m._2.describe()))

      readChoice(actions).map(_.perform(this))
    }
  }

  def println(s: String) {
    out.println(s)
  }
}


object Game {
  def main(args: Array[String]) {
    new Game(new BufferedReader(new InputStreamReader(System.in)), System.out).play()
  }
}
