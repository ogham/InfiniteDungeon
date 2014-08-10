package infinite.dungeon

import infinite.dungeon.monster.Monster
import infinite.dungeon.room.Room

import scala.collection.mutable
import scala.util.Random

/**
 * A Level is a horizontal slice of a multi-layer game world. It contains many
 * rooms that the player can explore.
 *
 * A Level is the class responsible for maintaining where the monsters are,
 * rather than, say, giving each Room object a collection of Monsters and
 * passing them around that way. Not only does this free up the Rooms from
 * having to remember what they have in them, but it also means there's only
 * one place where each monster can be -- you can't get the same one in
 * multiple rooms.
 *
 * @param rooms    sequence of rooms in this level
 * @param monsters mapping of monsters to the rooms they're in
 */
class Level(rooms: Seq[Room], monsters: mutable.Map[Monster, Room]) {

  /** Return all the monsters present in the given room. */
  def monstersIn(room: Room): Iterable[Monster] = {
    monsters.filter(_._2 == room).keys
  }

  /** Prints out a list of all rooms, and the creatures that lie within. */
  def describe() {
    rooms.foreach(room => { describeRoom(room); println(""); })
  }

  /** Prints out the type of the given room, and the monsters in it. */
  def describeRoom(room: Room) {
    println("Room #" + room.ID + " is a " + room.name() + ".")
    monstersIn(room).foreach(m => println("There is a " + m.name() + " here."))
  }
}

object Level {

  /**
   * Construct a random Level object, with a number of rooms, each filled
   * with various monsters.
   *
   * @param random the random source
   * @return a new Level object
   */
  def build(random: Random): Level = {
    val rooms: Seq[Room] = Seq.fill(5)(Room.randomRoom(random))
    var map = mutable.Map[Monster, Room]()

    rooms.foreach(room => {
      for (i <- 1 to random.nextInt(4)) {
        val monster = Monster.randomMonster(random)
        map += monster -> room
      }
    })

    new Level(rooms, map)
  }
}
