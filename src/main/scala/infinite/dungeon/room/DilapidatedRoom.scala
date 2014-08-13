package infinite.dungeon.room

class DilapidatedRoom(variant: Int) extends Room {
  override def name(): String = "dilapidated room"

  override def describe(): String = {
    variant match {
      case 0 => "This room's cobblestones are cracked and upturned, making the floor sharp and jagged. Perhaps the earth has shifted, or the weather has taken its toll over many years."
      case 1 => "This room has a large pile of rocks in the corner, and a corresponding hole in its ceiling. Clearly there was a weak point in the bedrock here."
      case 2 => "This room has a large pool in the middle and the water doesn't look good at all. There must be an underground river somewhere near here. You can still walk around the edges, though."
      case 3 => "This room has old, cracked stones for walls and pockmarks on the floor. It's the three deep, serrated gashes in the wall that make it, though."
      case 4 => "This room contains the twisted wreckage of an old gibbet, with a few fragments of old rope in among the splintered mess. The ceiling here is higher than other rooms to allow for the drop needed to kill a person."
    }
  }
}
