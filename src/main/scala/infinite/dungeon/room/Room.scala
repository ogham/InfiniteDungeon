package infinite.dungeon.room

import infinite.dungeon.action.Action

import scala.util.Random

object Room {
  private var currentID = 1000
  private def nextID = {
    currentID += 1
    currentID
  }

  def randomRoom(random: Random): Room = {
    val n = random.nextInt(10)
    if (n == 0 || n == 1 || n == 2) {
      new DilapidatedRoom(random.nextInt(3))
    }
    else if (n == 3 || n == 4 || n == 5) {
      new OrdinaryRoom(random.nextInt(3))
    }
    else if (n == 6) {
      new FountainRoom(random.nextInt(3))
    }
    else if (n == 7) {
      new Larder()
    }
    else if (n == 8) {
      new CratesRoom()
    }
    else { // if (n == 9)
      new Tannery()
    }
  }
}

/**
 * A room is something the player can explore in the game world.
 */
trait Room {
  val ID = Room.nextID
  def name(): String

  def describe(): String

  /** List of actions that the player can pick from just by being in the room. */
  def passiveActions(): Seq[Action] = {
    List()
  }

  override def equals(other: Any): Boolean = {
    other match {
      case that: Room => that.isInstanceOf[Room] && that.ID == this.ID
      case _ => false
    }
  }

  override def hashCode: Int = ID
  override def toString: String = ID + "/" + name().replace(' ', '_')
}
