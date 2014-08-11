package infinite.dungeon.monster

class Zombie extends Monster {
  override def name(): String = "zombie"

  override def describe(uppercase: Boolean): String = {
    "A zombie walks towards you with its arms raised."
  }
}
