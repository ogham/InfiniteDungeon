package infinite.dungeon.room

class DilapidatedRoom(variant: Int) extends Room {
  override def name(): String = "dilapidated room"

  override def describe(): String = {
    if (variant == 0) {
      "This room's cobblestones are cracked and upturned, making the floor sharp and jagged. Perhaps the earth has shifted, or the weather has taken its toll over many years."
    }
    else if (variant == 1) {
      "This room has a large pile of rocks in the corner, and a corresponding hole in its ceiling. Clearly there was a weak point in the bedrock here."
    }
    else {
      "This room has a large pool in the middle and the water doesn't look good at all. There must be an underground river somewhere near here. You can still walk around the edges, though."
    }
  }
}
