package infinite.dungeon

import infinite.dungeon.action.{Action, Attack, Move}
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
    var s = new mutable.StringBuilder(s"Room #${room.ID} is a ${room.name()}.")
    monstersIn(room).foreach(m => s.append(" " + m.describe(false)))
    s.toString()
  }

  /** Get all the actions for the given room. */
  def actionsForRoom(room: Room): Seq[Action] = {
    var actions: Seq[Action] = List()
    actions ++= directions.actionsForRoom(room)
    actions ++= monstersIn(room).map(new Attack(_))
    actions
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
  def build(startRoom: Room, random: Random): Level = {
    val rooms = new LevelBuilder(random).addRoom((10, 10), startRoom).addRooms(12).build()
    var map = mutable.Map[Monster, Room]()

    rooms.foreach(room => {
      if (random.nextBoolean()) {
        for (i <- 1 to random.nextInt(3)) {
          val monster = Monster.randomMonster(random)
          map += monster -> room._2
        }
      }
    })

    new Level(rooms, Directions.buildFromRoomsMap(rooms), map)
  }
}

/**
 * Instead of relying on the 2D grid that the LevelBuilder generates, a level
 * uses four maps of directions that say which rooms the player can move to.
 *
 * Each mapping takes the room the player is currently in as the key, and has
 * the room they would end up in going that way as the value. Note that there
 * aren't any (Int, Int) objects here, as we have done away with the grid!
 *
 * @param ups    rooms where the player can move north
 * @param downs  rooms where the player can move south
 * @param lefts  rooms where the player can move west
 * @param rights rooms where the player can move east
 */
class Directions(ups: mutable.Map[Room, Room],
                  downs: mutable.Map[Room, Room],
                  lefts: mutable.Map[Room, Room],
                  rights: mutable.Map[Room, Room]) {

  /** Produce a list of movement actions for the given room. */
  def actionsForRoom(room: Room): Seq[Action] = {
    var actions: Seq[Action] = List()

    if (ups.contains(room)) {
      actions = actions :+ new Move(Direction.North, ups(room))
    }

    if (downs.contains(room)) {
      actions = actions :+ new Move(Direction.South, downs(room))
    }

    if (lefts.contains(room)) {
      actions = actions :+ new Move(Direction.West, lefts(room))
    }

    if (rights.contains(room)) {
      actions = actions :+ new Move(Direction.East, rights(room))
    }

    actions
  }

}

object Directions {

  /** Given a map of positions to rooms, turn it into a Directions object. */
  def buildFromRoomsMap(rooms: mutable.Map[(Int, Int), Room]): Directions = {
    var ups: mutable.Map[Room, Room] = mutable.Map()
    var downs: mutable.Map[Room, Room] = mutable.Map()
    var lefts: mutable.Map[Room, Room] = mutable.Map()
    var rights: mutable.Map[Room, Room] = mutable.Map()

    rooms.foreach(room => {
      if (rooms.contains((room._1._1 - 1, room._1._2))) {
        ups.put(room._2, rooms((room._1._1 - 1, room._1._2)))
      }

      if (rooms.contains((room._1._1 + 1, room._1._2))) {
        downs.put(room._2, rooms((room._1._1 + 1, room._1._2)))
      }

      if (rooms.contains((room._1._1, room._1._2 - 1))) {
        lefts.put(room._2, rooms((room._1._1, room._1._2 - 1)))
      }

      if (rooms.contains((room._1._1, room._1._2 + 1))) {
        rights.put(room._2, rooms((room._1._1, room._1._2 + 1)))
      }
    })

    new Directions(ups, downs, lefts, rights)
  }
}
