package infinite.dungeon

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.ServerSocket

// Simple server

object Server {
  def main(args: Array[String]) {

    val server = new ServerSocket(9999)
    while (true) {
      val s = server.accept()
      val in = new BufferedReader(new InputStreamReader(s.getInputStream))
      val out = new PrintStream(s.getOutputStream)

      var game = new Game(in, out)
      game.play()
      s.close()
    }
  }
}
