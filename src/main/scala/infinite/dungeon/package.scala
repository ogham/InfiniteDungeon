package infinite

import infinite.dungeon.room.Room

import scala.collection.mutable

package object dungeon {
  type roomMap = mutable.Map[(Int, Int), Room]

  object Direction extends Enumeration {
    type Direction = Value
    val North, South, East, West = Value
  }
}
