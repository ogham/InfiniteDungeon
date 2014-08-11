package infinite.dungeon.action

import infinite.dungeon.Main

trait Action {
  def perform(main: Main.type)

  def describe(): String
}
