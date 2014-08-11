package infinite.dungeon.monster

class Skeleton extends Monster {
  override def name(): String = "skeleton"

  override def describe(uppercase: Boolean): String = {
    "A skeleton strides towards you, swinging a scimitar."
  }
}
