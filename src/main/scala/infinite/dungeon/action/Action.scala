package infinite.dungeon.action

import infinite.dungeon.Game

trait Action {
  def perform(game: Game)
  def describe(): String
}
