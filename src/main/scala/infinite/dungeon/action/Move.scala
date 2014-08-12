package infinite.dungeon.action

import infinite.dungeon.Direction.Direction
import infinite.dungeon.Game
import infinite.dungeon.room.Room

class Move(in: Direction, to: Room) extends Action {
  override def describe(): String = {
    if (to.seenByPlayer) {
      s"Move ${in.toString} (to the ${to.name})"
    }
    else {
      s"Move ${in.toString} (unexplored)"
    }
  }

  override def perform(game: Game): Unit = {
    game.println(s"You move $in.")
    game.currentRoom = to
    to.seenByPlayer = true

    game.println(game.level.describeRoom(to))
    game.println("")
  }
}
