package chat

import akka.actor.Props
import chat.controller.{BecomeActive, WelcomeController}
import chat.net.Client
import chat.swing.{Setup, Window}

/**
  * Created by nikolaykombarov on 19.08.17.
  */
object LaunchClient {

  def main(args: Array[String]): Unit = {
    val window = Client.system.actorOf(Props[Window], name = "window")
    val welcomeController = Client.system.actorOf(Props(new WelcomeController(window)))

    window ! Setup()
    welcomeController ! BecomeActive()
  }
}
