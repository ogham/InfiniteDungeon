package infinite.dungeon

import infinite.dungeon.action.Action
import infinite.dungeon.room.{Room, Dungeon}

import scala.util.Random

/**
 * Main program entry point.
 */
object Main {
  var currentRoom: Room = new Dungeon()
  val level = Level.build(currentRoom, new Random())
  var playing = true

  def readChoice(): Int = {
    while (true) {
      var input = readLine("> ")
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

  def main(args: Array[String]) {
    while (playing) {
      level.describeRoom(currentRoom)
      val actions: Seq[Action] = level.actionsForRoom(currentRoom)
      actions.zipWithIndex.foreach(m => println(m._2 + ") " + m._1.describe()))

      val choice = readChoice()
      if (choice >= 0) {
        actions(choice).perform(this)
      }
    }
  }
}
