package infinite.dungeon.action

import infinite.dungeon.Game
import infinite.dungeon.room.FountainRoom

class Quaff(fountainRoom: FountainRoom) extends Action {
  override def perform(game: Game): Unit = {
    if (game.random.nextInt(3) == 2) {
      fountainRoom.hasWater = false
      game.println(fountainRoom.lastDrinkMessage())
    }
    else {
      game.println(fountainRoom.drinkMessage())
    }

    game.healPlayer(game.random.nextInt(100))
  }

  override def describe(): String = fountainRoom.actionDescription()
}
