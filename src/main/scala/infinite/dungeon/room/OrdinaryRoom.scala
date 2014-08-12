package infinite.dungeon.room

class OrdinaryRoom(variant: Int) extends Room {
  override def name(): String = "ordinary room"

  override def describe(): String = {
    variant match {
      case 0 => "This room is bare, with marks on the floor and walls from old furniture and fittings that aren't here anymore; probably looted long ago."
      case 1 => "This room used to see a lot of footfall, judging by the heavy grooves cut into the floor. Perhaps it was a guard post, or a popular meeting place."
      case 2 => "This room has higher-quality cobblestones, either because the room was for a higher station or because the room never had many people in it. It's not pristine but it feels younger than the rest of this place."
      case 3 => "This room has a deliberately-shaped floor - several deep grooves, sloped slightly downward towards another, larger groove. The stone in these channels is discoloured. You can only imagine that it used to serve as a slaughterhouse."
    }
  }
}
