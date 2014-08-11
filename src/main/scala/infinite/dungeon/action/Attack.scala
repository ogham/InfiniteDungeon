package infinite.dungeon.action

import infinite.dungeon.Game
import infinite.dungeon.monster.Monster

class Attack(enemy: Monster) extends Action {
  override def describe(): String = "Attack the " + enemy.name()

  override def perform(game: Game) {
    game.println("You attack the " + enemy.name() + "!")
    game.level.killMonster(enemy)
  }
}
