package chat.controller

import java.awt.event.ActionEvent

import akka.actor.{Actor, ActorRef, Props}
import chat.window.WelcomePanel

/**
  * Created by nikolaykombarov on 20.08.17.
  */
class WelcomeController(val window: ActorRef) extends Controller with Actor {

  override def receive: Receive = {
    case BecomeActive() => super.becomeActive()
  }

  val onSubmit = { e: ActionEvent =>
    val playerName = panel.nameField.getText
    val serverAddress = panel.addressField.getText()

    val chatController = system.actorOf(Props(new ChatController(playerName, serverAddress, window)))
    chatController ! BecomeActive()
  }

  val panel: WelcomePanel = new WelcomePanel(onSubmit)
}