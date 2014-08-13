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
    random.nextInt(10) match {
      case 0 | 1 | 2 => new DilapidatedRoom(random.nextInt(5))
      case 3 | 4 | 5 => new OrdinaryRoom(random.nextInt(7))
      case 6 => new FountainRoom(random.nextInt(3))
      case 7 => new Larder()
      case 8 => new CratesRoom()
      case 9 => new Tannery()
    }
  }
}

/**
 * A room is something the player can explore in the game world.
 */
trait Room {
  val ID = Room.nextID
  var seenByPlayer = false
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
