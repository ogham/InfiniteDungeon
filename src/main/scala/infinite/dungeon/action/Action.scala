package infinite.dungeon.action

import infinite.dungeon.Game

trait Action {
  def perform(main: Game)
  def describe(): String
}
