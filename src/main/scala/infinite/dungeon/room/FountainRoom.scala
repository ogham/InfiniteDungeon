package infinite.dungeon.room

import infinite.dungeon.action.{Quaff, Action}

class FountainRoom extends Room {
  var hasFountain = true

  def dryUpFountain() {
    hasFountain = false
  }

  override def passiveActions(): Seq[Action] = {
    if (hasFountain) {
      super.passiveActions() ++ List(new Quaff(this))
    }
    else {
      super.passiveActions()
    }
  }

  override def name(): String = {
    if (hasFountain) {
      "fountain room"
    }
    else {
      "room"  // heh
    }
  }
}
