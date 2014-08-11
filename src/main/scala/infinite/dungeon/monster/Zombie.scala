package infinite.dungeon.monster

class Zombie extends Monster {
  override def name(): String = "zombie"

  override def describe(uppercase: Boolean): String = {
    "A zombie walks towards you with its arms raised."
  }

  override def initialHP(): Int = 20  // Zombies are a bit tougher

  override def killMessage(): String = "You hit the zombie! It looks dazed, and then falls over."

  override def hitMessage(): String = "You hit the zombie!"
}
