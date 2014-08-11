package infinite.dungeon.room

import infinite.dungeon.action.{Quaff, Action}

class FountainRoom(variant: Int) extends Room {

  override def passiveActions(): Seq[Action] = {
    super.passiveActions() ++ List(new Quaff(this))
  }

  override def name(): String = "fountain room"

  override def describe(): String = {
    if (variant == 0) {
      "This well-lit room has a large stone plinth in the dead centre, with a cracked but serviceable stone bowl on top. The water contained within looks pure. There are no drinking vessels left in this room - but it's not like you really need one."
    }
    else if (variant == 1) {
      "This well-lit room has a white stone needle suspended from its ceiling. Pure, sparkling water trickles from the tip and cascades into a small circular hole in the floor. The hole is lined with shaped bricks and chalk symbols."
    }
    else {
      "This well-lit room has a lion's head on the wall, disgorging pure, clean waher into a stone bowl, which drains away to somewhere unknown."
    }
  }
}
