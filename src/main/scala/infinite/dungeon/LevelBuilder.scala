package infinite.dungeon

import infinite.dungeon.room.Room

import scala.collection.mutable
import scala.util.Random

/**
 * A level builder creates a 2D map of positions to rooms. Its algorithm
 * currently ensures that no two paths will ever link up, creating the
 * appearance of a sprawling, labyrinthine dungeon.
 *
 * @param random randomness source
 */
class LevelBuilder(random: Random) {

  /** Mapping of positions to the rooms at them. */
  var rooms: mutable.Map[(Int, Int), Room] = mutable.Map()

  /** List of areas where rooms could be generated. */
  var possibilities: Seq[(Int, Int)] = List()

  /**
   * Adds a new room to the map at the given position, and adds the available
   * positions around it to the possibilities list.
   *
   * @param position position at which the room should be
   * @param room     what class of room it is
   */
  def addRoom(position: (Int, Int), room: Room): LevelBuilder = {
    rooms += position -> room

    // First, remove the room from the list of possibilities.
    possibilities = possibilities.filter(pos => pos != position).++(around(position))

    // Next, add the positions around that one to the list...
    possibilities = possibilities ++ around(position)

    // ...but then remove all of the ones around *those*, so no two paths will ever link up.
    possibilities = possibilities.filter(pos => roomPossibility(around(pos).count(p => rooms.contains(p))) && !rooms.contains(pos))

    this
  }

  /**
   * Determine whether a room should be build based on its number of neighbours.
   *
   * If you want to create differently-styled rooms, then this is the function
   * that you should be playing around with:
   *
   * Restricting the neighbour count to 1 or lower creates multiple corridors
   * that don't ever link up.
   *
   * Making it 2 or lower seems like it should create some loops, but it
   * actually has the effect of making the corridors 2 or more wide, creating
   * lots and lots of 2x2 squares. The best way to fix this would be to include
   * reachable diagonals in the neighbours count, but that's not done yet.
   *
   * Making it 3 or lower just creates big blobby levels.
   */
  private def roomPossibility(neighboursCount: Int): Boolean = {
    neighboursCount <= 1
  }

  /**
   * Adds the given number of rooms to the level.
   *
   * @param number how many rooms to add
   */
  def addRooms(number: Int): LevelBuilder = {
    for (i <- 0 to number) {
      var index = random.nextInt(possibilities.length)        // Pick a random room from the possibilites list...
      addRoom(possibilities(index), Room.randomRoom(random))  // ...and add it
    }

    this
  }

  /** Return the four positions around the given one. */
  def around(position: (Int, Int)): Seq[(Int, Int)] = {
    List(
      (position._1 + 1, position._2),
      (position._1 - 1, position._2),
      (position._1, position._2 - 1),
      (position._1, position._2 + 1)
    )
  }

  /** Return a build layout of this level. */
  def build(): mutable.Map[(Int, Int), Room] = rooms.clone()

  /**
   * Print out a grid view of the level so far, showing both possibilities and
   * created rooms.
   */
  def debug(): LevelBuilder = {
    val miny: Int = (rooms.map(_._1._2) ++ possibilities.map(_._2)).min
    val maxy: Int = (rooms.map(_._1._2) ++ possibilities.map(_._2)).max
    val minx: Int = (rooms.map(_._1._1) ++ possibilities.map(_._1)).min
    val maxx: Int = (rooms.map(_._1._1) ++ possibilities.map(_._1)).max

    for (j <- miny to maxy) {
      for (i <- minx to maxx) {
        if (possibilities.contains((i, j))) {
          print("?")
        }
        else if (rooms.contains((i, j))) {
          print("#")
        }
        else {
          print(" ")
        }
      }

      println("")
    }

    this
  }
}
