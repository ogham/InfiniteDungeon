package infinite.dungeon

import scala.util.Random

/**
 * Main program entry point.
 */
object Main {
  def main(args: Array[String]) {
    val level = Level.build(new Random())
    level.describe()
  }
}
