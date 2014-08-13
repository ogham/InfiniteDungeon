package infinite.dungeon.monster

import infinite.dungeon.Game

class Ghost(variant: Int) extends Monster {
  override def name(): String = "ghost"

  override def describe(uppercase: Boolean): String = {
    variant match {
      case 0 => "The light from the glowrock torches seems to coalesce into a single form. A form that looks at you as it solidifies into the shape of a young human, then opens its mouth and screams without sound."
      case 1 => "A hound made entirely of ethereal light paces the room, occasionally clawing at the air in doorframes and the space between wall-stones. The skeleton curled up in one corner tells you that the creature's owner never did come back."
      case 2 => "A figure sits, leaning back against a wall. She glances at you, opens her mouth as if to speak, then looks back at the floor. She clearly has no interest in your presence."
    }
  }

  override def withPlayerNearby(game: Game): Boolean = false
}
