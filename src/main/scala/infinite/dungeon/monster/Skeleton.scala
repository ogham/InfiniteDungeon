package infinite.dungeon.monster

import infinite.dungeon.Game

class Skeleton(variant: Int) extends EnemyMonster {
  override def name(): String = "skeleton"

  override def describe(uppercase: Boolean): String = {
    variant match {
      case 0 => "A non-human skeleton, held together by unknown magic, approaches you. Its motion is jerky and unpredictable but it is clearly hostile."
      case 1 => "A human skeleton, animated by an unknown force, clatters toward you. It's missing bones, but none of the remaining ones are broken. You wonder how many more you'll have to break before it leaves you alone."
      case 2 => "A skeleton, assembled from a mish-mash of different species, judging by its lopsidedness, lurches toward you. It looks angry - inasfar as a featureless skull can."
    }
  }

  override def initialHP(): Int = 10  // Skeletons are pretty weak

  override def killMessage(): String = "You hit the skeleton hard on the skull! It shatters, and the skeleton collapses into a heap of bones on the floor."

  override def hitMessage(): String = "You hit the skeleton!"

  override def withPlayerNearby(game: Game): Boolean = {
    game.println("The skeleton slashes you!")
    game.maimPlayer(20)
    true
  }
}
