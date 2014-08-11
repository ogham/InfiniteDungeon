package infinite.dungeon.action

import infinite.dungeon.Game
import infinite.dungeon.room.FountainRoom

class Quaff(fountainRoom: FountainRoom) extends Action {
  override def perform(game: Game): Unit = {
    game.println("You drink from the fountain.")
    game.healPlayer(game.random.nextInt(20))
    if (game.random.nextInt(3) == 0) {
      game.println("The fountain dries up!")
      fountainRoom.dryUpFountain()
    }
  }

  override def describe(): String = "Drink from the fountain"
}
