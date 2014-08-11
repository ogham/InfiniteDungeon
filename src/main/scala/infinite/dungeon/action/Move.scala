package infinite.dungeon.action

import infinite.dungeon.Direction.Direction
import infinite.dungeon.Main
import infinite.dungeon.room.Room

class Move(in: Direction, to: Room) extends Action {
  override def describe(): String = "Move " + in.toString + " (to " + to.ID + ")"

  override def perform(main: Main.type): Unit = {
    main.currentRoom = to
  }
}
