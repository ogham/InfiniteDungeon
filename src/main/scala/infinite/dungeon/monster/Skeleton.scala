package infinite.dungeon.monster

class Skeleton extends Monster {
  override def name(): String = "skeleton"

  override def describe(uppercase: Boolean): String = {
    "A skeleton strides towards you, swinging a scimitar."
  }

  override def initialHP(): Int = 10  // Skeletons are pretty weak

  override def killMessage(): String = "You hit the skeleton hard on the skull! It shatters, and the skeleton collapses into a heap of bones on the floor."

  override def hitMessage(): String = "You hit the skeleton!"
}
