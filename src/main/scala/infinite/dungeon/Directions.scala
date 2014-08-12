package infinite.dungeon

import infinite.dungeon.action.{Action, Move}
import infinite.dungeon.room.Room

import scala.collection.{mutable, immutable}

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
class Directions(ups: immutable.Map[Room, Room],
                 downs: immutable.Map[Room, Room],
                 lefts: immutable.Map[Room, Room],
                 rights: immutable.Map[Room, Room]) {

  /** Produce a list of movement actions for the given room. */
  def actionsForRoom(room: Room): immutable.Map[Char, Action] = {
    var actions = immutable.Map.newBuilder[Char, Action]

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

    actions.result()
  }

}

object Directions {

  /** Given a map of positions to rooms, turn it into a Directions object. */
  def buildFromRoomsMap(rooms: mutable.Map[(Int, Int), Room]): Directions = {
    val ups = immutable.Map.newBuilder[Room, Room]
    val downs = immutable.Map.newBuilder[Room, Room]
    val lefts = immutable.Map.newBuilder[Room, Room]
    val rights = immutable.Map.newBuilder[Room, Room]

    rooms.foreach(room => {
      if (rooms.contains((room._1._1 - 1, room._1._2))) {
        ups += room._2 -> rooms((room._1._1 - 1, room._1._2))
      }

      if (rooms.contains((room._1._1 + 1, room._1._2))) {
        downs += room._2 -> rooms((room._1._1 + 1, room._1._2))
      }

      if (rooms.contains((room._1._1, room._1._2 - 1))) {
        lefts += room._2 -> rooms((room._1._1, room._1._2 - 1))
      }

      if (rooms.contains((room._1._1, room._1._2 + 1))) {
        rights += room._2 -> rooms((room._1._1, room._1._2 + 1))
      }
    })

    new Directions(ups.result(), downs.result(), lefts.result(), rights.result())
  }
}
