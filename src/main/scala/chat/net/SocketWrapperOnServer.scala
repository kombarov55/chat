package chat.net

import java.net.Socket

class SocketWrapperOnServer(socket: Socket) extends SocketWrapper(socket) {

  override def receiveCreatingMessage(newMessage: CreatingMessage): Unit = {
    context.parent ! newMessage
  }

  override def receiveBroadcastMessage(spreadMessage: BroadcastingMessage): Unit = {
    super.sendToTheOtherEnd(spreadMessage)
  }
}
