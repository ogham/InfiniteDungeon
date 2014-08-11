package infinite.dungeon.action

import infinite.dungeon.monster.Monster

class Attack(enemy: Monster) extends Action {
  override def describe(): String = "Attack the " + enemy.name()
}
