package infinite.dungeon.monster

import infinite.dungeon.Game

class Zombie(variant: Int) extends Monster {
  override def name(): String = "zombie"

  override def describe(uppercase: Boolean): String = {
    variant match {
      case 0 => "A corpse lumbers towards you. Fortunately, it's been dead for long enough the flesh is dry and papery. There's no stench and no wriggling. You start to chuckle at the absurdity of considering this to be a good situation - then it goes for your face."
      case 1 => "A corpse, non-human, shambles in your general direction, skewing a little to the left as it does. It seems like it should be harmless, but you know the unnatural strength it possesses."
      case 2 => "A corpse drags itself toward you across the floor. Its legs are almost gone, clearly having fallen victim to the local rat population. You'd feel sorry for it, but you know it's still going to make a spirited (ha) attempt at killing you."
    }
  }

  override def initialHP(): Int = 20  // Zombies are a bit tougher

  override def killMessage(): String = "You hit the zombie! It looks dazed, and then falls over."

  override def hitMessage(): String = "You hit the zombie!"

  override def withPlayerNearby(game: Game): Boolean = {
    game.println("The zombie punches you in the face!")
    game.maimPlayer(13)
    true
  }
}
