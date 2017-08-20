package chat

import akka.actor.{ActorSystem, Props}
import chat.controller.WelcomeController
import chat.net.{Client, Host}
import chat.window.Window

/**
  * Created by nikolaykombarov on 19.08.17.
  */
object Launcher {

  def main(args: Array[String]): Unit = {
    launchServer()
    Window.setActiveController(Client.system.actorOf(Props[WelcomeController]))
  }

  def launchServer(): Unit = {
    val host = Client.system.actorOf(Props(new Host(6666)), name = "host")
    host ! "start"
  }
}
