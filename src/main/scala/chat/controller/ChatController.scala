package chat.controller

import java.awt.event.ActionEvent
import java.net.Socket

import akka.actor.{Actor, ActorRef, Props}
import chat.net._
import chat.window.ChatPanel

class ChatController(playerName: String, serverAddress: String, val window: ActorRef) extends Controller with Actor {

  def displayMessage(message: Message): Unit = {
    panel.listModel.addElement(s"${message.creationTime}::${message.author} = ${message.text}")
  }


  override def receive: Receive = {
    case BroadcastingMessage(message) => displayMessage(message)
    case BecomeActive() => super.becomeActive()
  }

  def onSubmit = { e: ActionEvent =>
    clientSocket ! CreatingMessage(Message(playerName, panel.inputField.getText))
    panel.inputField.setText("")

  }

  val socket = new Socket(serverAddress, 6666)
  val clientSocket = context.actorOf(Props(new SocketWrapperOnClient(socket)), name = "clientSocket")

  val panel: ChatPanel = new ChatPanel(onSubmit)

}
