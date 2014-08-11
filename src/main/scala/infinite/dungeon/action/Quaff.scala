package infinite.dungeon.action

import infinite.dungeon.Game
import infinite.dungeon.room.FountainRoom

class Quaff(fountainRoom: FountainRoom) extends Action {
  override def perform(game: Game): Unit = {
    game.println("You drink from the fountain, and feel much better.")
    game.healPlayer(game.random.nextInt(100))
  }

  override def describe(): String = "Drink from the fountain"
}
