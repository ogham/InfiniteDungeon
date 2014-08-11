package infinite.dungeon.action

import infinite.dungeon.Main
import infinite.dungeon.monster.Monster

class Attack(enemy: Monster) extends Action {
  override def describe(): String = "Attack the " + enemy.name()

  override def perform(main: Main.type) {
    println("You attack the " + enemy.name() + "!")
    main.level.killMonster(enemy)
  }
}
