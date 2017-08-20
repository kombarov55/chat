package chat.controller

import java.awt.event.ActionEvent
import java.net.Socket

import akka.actor.{Actor, Props}
import chat.net._
import chat.window.{ChatPanel, Window}

class ChatController extends Controller with Actor {

  def displayMessage(message: Message): Unit = {
    panel.listModel.addElement(s"${message.creationTime}::${message.author} = ${message.text}")
  }


  override def receive: Receive = {
    case BroadcastingMessage(message) => displayMessage(message)
    case "setup" => setup()
    case "clear" => clear()
  }

  def onSubmit = { e: ActionEvent =>
    clientSocket ! CreatingMessage(Message(Client.playerName, panel.inputField.getText))
    panel.inputField.setText("")

  }

  val socket = new Socket(Client.serverAddress, 6666)
  val clientSocket = context.actorOf(Props(new SocketWrapperOnClient(socket)), name = "clientSocket")

  val panel: ChatPanel = new ChatPanel(onSubmit)

  override def setup(): Unit = {
    Window.add(panel)
    Window.pack()
  }

  override def clear(): Unit = {
    Window.remove(panel)
  }

}
