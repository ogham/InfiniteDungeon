package infinite.dungeon.room

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

  override def equals(other: Any): Boolean = {
    other match {
      case that: Room => that.isInstanceOf[Room] && that.ID == this.ID
      case _ => false
    }
  }

  override def hashCode: Int = ID
  override def toString: String = ID + "/" + name().replace(' ', '_')
}
