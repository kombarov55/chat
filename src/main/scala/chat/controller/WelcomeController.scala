package chat.controller

import java.awt.event.ActionEvent

import akka.actor.{Actor, Props}
import chat.net.Client
import chat.window.{WelcomePanel, Window}

/**
  * Created by nikolaykombarov on 20.08.17.
  */
class WelcomeController extends Controller with Actor {


  override def receive: Receive = {
    case "setup" => setup()
    case "clear" => clear()
  }

  val onSubmit = { e: ActionEvent =>
    Client.playerName = panel.nameField.getText
    Client.serverAddress = panel.addressField.getText()
    Window.setActiveController(Client.system.actorOf(Props[ChatController]))
  }

  val panel: WelcomePanel = new WelcomePanel(onSubmit)

  override def setup(): Unit = {
    Window.add(panel)
    Window.pack()
  }

  override def clear(): Unit = {
    Window.remove(panel)
  }
}