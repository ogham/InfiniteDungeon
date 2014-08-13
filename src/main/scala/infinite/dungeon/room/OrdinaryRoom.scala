package infinite.dungeon.room

class OrdinaryRoom(variant: Int) extends Room {
  override def name(): String = "ordinary room"

  override def describe(): String = {
    variant match {
      case 0 => "This room is bare, with marks on the floor and walls from old furniture and fittings that aren't here anymore; probably looted long ago."
      case 1 => "This room used to see a lot of footfall, judging by the heavy grooves cut into the floor. Perhaps it was a guard post, or a popular meeting place."
      case 2 => "This room has higher-quality cobblestones, either because the room was for a higher station or because the room never had many people in it. It's not pristine but it feels younger than the rest of this place."
      case 3 => "This room has a deliberately-shaped floor - several deep grooves, sloped slightly downward towards another, larger groove. The stone in these channels is discoloured. You can only imagine that it used to serve as a slaughterhouse."
      case 4 => "This room contains nothing but the smell of mildew and a few torches on the walls."
      case 5 => "This room is well-built. The stones that make up the wall are in good condition though they have had short messages in old languages carved into them. Some look like insults, others like dedications. Perhaps this was a social place, or sleeping quarters."
      case 6 => "This room has no torches on the walls. The little glowing stones have been cannibalised, used instead to lay out a glowing geometric pattern on the floor, as though for some old ritual."
    }
  }
}
