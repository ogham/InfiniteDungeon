package infinite.dungeon.action

import infinite.dungeon.Game
import infinite.dungeon.monster.EnemyMonster

class Attack(enemy: EnemyMonster) extends Action {
  override def describe(): String = "Attack the " + enemy.name()

  override def perform(game: Game) {
    val attackStrength = 12

    if (enemy.HP <= attackStrength) {
      game.println(enemy.killMessage())
      game.level.killMonster(enemy)
    }
    else {
      game.println(enemy.hitMessage())
      enemy.HP -= attackStrength
    }
  }
}
