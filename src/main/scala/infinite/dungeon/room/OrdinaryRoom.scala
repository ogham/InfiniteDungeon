package infinite.dungeon.room

class OrdinaryRoom(variant: Int) extends Room {
  override def name(): String = "ordinary room"

  override def describe(): String = {
    if (variant == 0) {
      "This room is bare, with marks on the floor and walls from old furniture and fittings that aren't here anymore; probably looted long ago."
    }
    else if (variant == 1) {
      "This room used to see a lot of footfall, judging by the heavy grooves cut into the floor. Perhaps it was a guard post, or a popular meeting place."
    }
    else {
      "This room has a deliberately-shaped floor - several deep grooves, sloped slightly downward towards another, larger groove. The stone in these channels is discoloured. You can only imagine that it used to serve as a slaughterhouse."
    }
  }
}
