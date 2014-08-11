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
  def healPlayer(amount: Int) {
    playerHP += amount

    if (playerHP > maxHP) {
      playerHP = maxHP
    }
  }

  def maimPlayer(damage: Int) {
    if (playerHP <= damage) {
      println("You have died...")  // Do you want your possessions identified?
      playing = false
    }
    else {
      playerHP -= damage
    }
  }

  var currentRoom: Room = new Dungeon()
  var maxHP = 100
  var playerHP = maxHP
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
    out.println(level.describeRoom(currentRoom))
    out.println("")

    while (playing) {
      val actions: mutable.Map[Char, Action] = level.actionsForRoom(currentRoom)
      val order = "NSWE0123456789"
      val sortedActions: Seq[(Char, Action)] = actions.toSeq.sortBy(a => order.indexOf(a._1))
      sortedActions.foreach(m => out.println(m._1 + ") " + m._2.describe()))

      // Do what the player ordered
      readChoice(actions).map(_.perform(this))

      // Now, go through and have all the monsters in this room (if there are
      // any) act, which usually means attacking the player. Because there
      // could be no monsters, we need to keep track of whether anything was
      // printed out, otherwise there will be two line breaks in a row, which
      // looks weird.

      var printExtraNewline = false
      level.monstersIn(currentRoom).foreach(m => {
        if (m.withPlayerNearby(this)) {
          printExtraNewline = true
        }
      })

      if (printExtraNewline) {
        println("")
      }
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
