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
    if (random.nextBoolean()) {
      new Dungeon()
    }
    else if (random.nextBoolean()) {
      new FountainRoom
    }
    else {
      new RockyRoom()
    }
  }
}

/**
 * A room is something the player can explore in the game world.
 */
trait Room {
  val ID = Room.nextID
  def name(): String

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
