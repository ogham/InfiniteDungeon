package infinite.dungeon

import infinite.dungeon.action.{Action, Attack}
import infinite.dungeon.monster.Monster
import infinite.dungeon.room.Room

import scala.collection.{immutable, GenTraversableOnce, mutable}
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
class Level(rooms: roomMap, directions: Directions, monsters: mutable.Map[Monster, Room]) {

  /** Return all the monsters present in the given room. */
  def monstersIn(room: Room): Iterable[Monster] = {
    monsters.filter(_._2 == room).keys
  }

  /** Prints out a list of all rooms, and the creatures that lie within. */
  def describeAll() {
    rooms.foreach(room => { describeRoom(room._2); println(""); })
  }

  /** Prints out the type of the given room, and the monsters in it. */
  def describeRoom(room: Room): String = {
    var s = new mutable.StringBuilder(room.describe())
    monstersIn(room).foreach(m => s.append("\n" + m.describe(uppercase = false)))
    s.toString()
  }

  /** Get all the actions for the given room. */
  def actionsForRoom(room: Room): immutable.Map[Char, Action] = {
    var actions = immutable.Map.newBuilder[Char, Action]
    actions ++= directions.actionsForRoom(room)

    var extraActions: Seq[Action] = List()
    extraActions ++= room.passiveActions()
    extraActions ++= monstersIn(room).map(new Attack(_))
    extraActions.zipWithIndex.foreach(m => actions += numToChar(m._2 + 1) -> m._1)
    actions.result()
  }

  private def numToChar(num: Int): Char = {
    if (num >= 1 && num <= 9) {
      num.toString.charAt(0)
    }
    else {
      throw new IllegalArgumentException("Number out of range")
    }
  }

  /** Remove a monster from the level. */
  def killMonster(monster: Monster) {
    monsters.remove(monster)
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
  def build(startRoom: Room, random: Random, numRooms: Int = 12): Level = {
    val rooms = new LevelBuilder(random).addRoom((10, 10), startRoom).addRooms(numRooms).build()
    var map = rooms.flatMap(room => monstersForRoom(random, room._2))
    new Level(rooms, Directions.buildFromRoomsMap(rooms), map)
  }

  /**
   * Generate a list of monsters for this room from a random source.
   *
   * @param random the random source
   * @param room   room to fill with monsters
   * @return list of monster -> room pairings
   */
  private def monstersForRoom(random: Random, room: Room): GenTraversableOnce[(Monster, Room)] = {
    if (random.nextInt(2) == 1) {
      List(Monster.randomMonster(random) -> room)
    }
    else {
      List()
    }
  }
}
