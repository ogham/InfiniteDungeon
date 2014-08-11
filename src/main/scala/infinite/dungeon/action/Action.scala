package infinite.dungeon.action

import infinite.dungeon.Game

/**
 * An Action is something that the player can do each turn.
 */
trait Action {
  def perform(game: Game)
  def describe(): String
}
