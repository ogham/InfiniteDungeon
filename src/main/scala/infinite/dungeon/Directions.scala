package infinite.dungeon

import infinite.dungeon.action.{Action, Move}
import infinite.dungeon.room.Room

import scala.collection.mutable

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
  def actionsForRoom(room: Room): mutable.Map[Char, Action] = {
    var actions: mutable.Map[Char, Action] = mutable.Map()

    if (ups.contains(room)) {
      actions += 'N' -> new Move(Direction.North, ups(room))
    }

    if (downs.contains(room)) {
      actions += 'S' -> new Move(Direction.South, downs(room))
    }

    if (lefts.contains(room)) {
      actions += 'W' -> new Move(Direction.West, lefts(room))
    }

    if (rights.contains(room)) {
      actions += 'E' -> new Move(Direction.East, rights(room))
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
