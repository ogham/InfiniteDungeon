package infinite.dungeon.room

import infinite.dungeon.action.{Quaff, Action}

class FountainRoom(variant: Int) extends Room {
  def actionDescription(): String = {
    variant match {
      case 0 => "Drink from the bowl"
      case 1 => "Drink from the hole"
      case 2 => "Drink from the fountain"
    }
  }

  var hasWater = true

  override def passiveActions(): Seq[Action] = {
    if (hasWater) {
      super.passiveActions() ++ List(new Quaff(this))
    }
    else {
      super.passiveActions()
    }
  }

  override def name(): String = "fountain room"

  override def describe(): String = {
    if (hasWater) {
      variant match {
        case 0 => "This well-lit room has a large stone plinth in the dead centre, with a cracked but serviceable stone bowl on top. The water contained within looks pure. There are no drinking vessels left in this room - but it's not like you really need one."
        case 1 => "This well-lit room has a white stone needle suspended from its ceiling. Pure, sparkling water trickles from the tip and cascades into a small circular hole in the floor. The hole is lined with shaped bricks and chalk symbols."
        case 2 => "This well-lit room has a lion's head on the wall, disgorging pure, clean water into a stone bowl, which drains away to somewhere unknown."
      }
    }
    else {
      variant match {
        case 0 => "This well-lit room has a large stone plinth in the dead centre, with a cracked but serviceable stone bowl on top. On closer inspection, unfortunately, the bowl is bone dry."
        case 1 => "This well-lit room has a white stone needle suspended from its ceiling. There is a creaking sound as a mechanism tries to activate - but nothing happens."
        case 2 => "This well-lit room has a lion's head on the wall with a stone bowl beneath it. It used to be a drinking fountain but there's no water left."
      }
    }
  }

  def drinkMessage(): String = {
    variant match {
      case 0 => "You cup your hands and take several mouthfuls of cool, fresh water from the bowl."
      case 1 => "You drop to the floor and drink from the bowl. You hear a click from somewhere as the bowl refills."
      case 2 => "You take a good drink of water from the bowl underneath the lion's head. A fresh stream of water flows down to replace it."
    }
  }

  def lastDrinkMessage(): String = {
    variant match {
      case 0 => "You cup your hands and take the last few mouthfuls of cool, fresh water from the bowl, leaving the stone to slowly dry."
      case 1 => "You drop to the floor and drink the last of the water from the bowl. You hear a creaking sound as a mechanism tries to activate - but the bowl remains dry."
      case 2 => "You take a good drink of water from the bowl underneath the lion's head, but the water slows to a trickle, and then stops completely."
    }
  }
}
