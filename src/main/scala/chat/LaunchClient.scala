package chat

import akka.actor.Props
import chat.controller.WelcomeController
import chat.net.Client
import chat.window.Window

/**
  * Created by nikolaykombarov on 19.08.17.
  */
object LaunchClient {

  def main(args: Array[String]): Unit = {
    Window.setActiveController(Client.system.actorOf(Props[WelcomeController]))
  }

}
