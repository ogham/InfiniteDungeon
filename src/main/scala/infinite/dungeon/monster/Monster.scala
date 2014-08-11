package infinite.dungeon.monster

import scala.util.Random

object Monster {
  private var currentID = 0
  private def nextID = {
    currentID += 1
    currentID
  }

  /** Generates a random monster */
  def randomMonster(random: Random): Monster = {
    // There are only two right now...
    if (random.nextBoolean()) {
      new Skeleton()
    }
    else {
      new Zombie()
    }
  }
}

/**
 * A monster is a scary thing that roams the dungeons.
 */
trait Monster {
  val ID = Monster.nextID
  def name(): String

  def initialHP(): Int
  var HP = initialHP()

  def describe(uppercase: Boolean): String

  def killMessage(): String
  def hitMessage(): String

  override def equals(other: Any): Boolean = {
    other match {
      case that: Monster => that.isInstanceOf[Monster] && that.ID == this.ID
      case _ => false
    }
  }

  override def hashCode: Int = ID
  override def toString: String = ID + "/" + name().replace(' ', '_')
}
