package infinite.dungeon.monster

import infinite.dungeon.Game
import infinite.dungeon.action.{Action, Attack}

import scala.util.Random

object Monster {
  private var currentID = 0
  private def nextID = {
    currentID += 1
    currentID
  }

  /** Generates a random monster */
  def randomMonster(random: Random): Monster = {
    random.nextInt(7) match {
      case 0 | 1 | 2 => new Skeleton(random.nextInt(3))
      case 3 | 4 | 5 => new Zombie(random.nextInt(3))
      case 6 => new Ghost(random.nextInt(3))
    }
  }
}

/**
 * A monster is a scary thing that roams the dungeons.
 */
trait Monster {
  val ID = Monster.nextID
  def name(): String

  /**
   * Message to print out after the room description, describing what this
   * monster is doing. Optionally in uppercase.
   */
  def describe(uppercase: Boolean): String

  /**
   * What this monster should do when the player is in the same room as them.
   *
   * @param game current game state to modify
   * @return whether anything has been printed out by this action
   */
  def withPlayerNearby(game: Game): Boolean
  
  /** List of actions that the player can pick from just by being in the room. */
  def actions(): Seq[Action] = {
    List()
  }

  override def equals(other: Any): Boolean = {
    other match {
      case that: Monster => that.isInstanceOf[Monster] && that.ID == this.ID
      case _ => false
    }
  }

  override def hashCode: Int = ID
  override def toString: String = ID + "/" + name().replace(' ', '_')
}

trait EnemyMonster extends Monster {
  /** Message to print out when the player lands a blow strong enough to kill this monster */
  def killMessage(): String

  /** Message to print out when the player hits, but does not kill, this monster */
  def hitMessage(): String
  
  /** HP to give this monster when it's spawned */
  def initialHP(): Int

  /** Its current HP */
  var HP = initialHP()
  
  override def actions(): Seq[Action] = {
    super.actions() ++ List(new Attack(this))
  }
}
