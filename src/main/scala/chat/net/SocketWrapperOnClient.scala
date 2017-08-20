package chat.net

import java.net.Socket

class SocketWrapperOnClient(socket: Socket) extends SocketWrapper(socket) {

  override def receiveCreatingMessage(newMessage: CreatingMessage): Unit = {
    sendToTheOtherEnd(newMessage)
  }

  override def receiveBroadcastMessage(broadcastingMessage: BroadcastingMessage): Unit = {
   context.parent ! broadcastingMessage
  }

}
