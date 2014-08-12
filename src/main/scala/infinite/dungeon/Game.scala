package infinite.dungeon

import java.io.{BufferedReader, InputStreamReader, PrintStream}

import infinite.dungeon.action.Action
import infinite.dungeon.room.{OrdinaryRoom, Room}

import scala.collection.mutable
import scala.util.Random

/**
 * A game holds the main game loop, as well as any variables that get modified
 * during the course of play.
 *
 * Because Infinite Dungeon can be run either locally or over a network, it
 * gets initialised with both input and output streams, which should definitely
 * be used instead of readLine() and println(), because they will get set to be
 * sent over a network socket in the case of a network game.
 *
 * @param in  buffered reader to read the player's input lines
 * @param out stream for any output lines
 */
class Game(in: BufferedReader, out: PrintStream) {
  /** The room we're currently in. */
  var currentRoom: Room = new OrdinaryRoom(0)

  /** The maximum the player's HP is allowed to go. */
  private var maxHP = 100

  /** The player's current HP. */
  var playerHP = maxHP

  /** Randomness generator that gets used all over the place. */
  var random = new Random()

  /** The level on which we are playing. */
  val level = Level.build(currentRoom, random)

  /** Whether we are, in fact, playing at all! */
  private var playing = true

  /** Read one of the player's choices from the game's input. */
  private def readChoice(choices: mutable.Map[Char, Action]): Option[Action] = {
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

  /** Start the main game loop, only leaving it when the game is over! */
  def play() {
    describeCurrentRoom()
    currentRoom.seenByPlayer = true

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

  def describeCurrentRoom() {
    out.println(level.describeRoom(currentRoom))
    out.println("")
  }

  /** Prints a string to the game's output */
  def println(s: String) {
    out.println(s)
  }

  /** Heals the player up to their max HP by the given amount. */
  def healPlayer(amount: Int) {
    playerHP += amount

    if (playerHP > maxHP) {
      playerHP = maxHP
    }
  }

  /** Damages the player, possibly killing them, by the given amount. */
  def maimPlayer(damage: Int) {
    if (playerHP <= damage) {
      println("You have died...")  // Do you want your possessions identified?
      playing = false
    }
    else {
      playerHP -= damage
    }
  }
}

object Game {
  /** Start a new local game without a server */
  def main(args: Array[String]) {
    new Game(new BufferedReader(new InputStreamReader(System.in)), System.out).play()
  }
}
