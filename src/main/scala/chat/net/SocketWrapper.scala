package chat.net

import java.io._
import java.net.Socket

import akka.actor.{Actor, ActorSystem}
import chat.Implicits.Function2Runnable

abstract class SocketWrapper(socket: Socket) extends Actor {

  val system = ActorSystem("mySystem")

  val toSocket = new ObjectOutputStream(socket.getOutputStream)
  val fromSocket = new ObjectInputStream(socket.getInputStream)

  launchListener()


  override def receive: Receive = {
    case msg: CreatingMessage => receiveCreatingMessage(msg)
    case msg: BroadcastingMessage => receiveBroadcastMessage(msg)
  }


  def launchListener(): Unit = {
    new Thread(() => {
      while (true) {
        self ! fromSocket.readObject()
      }
    }).start()
  }

  def sendToTheOtherEnd(obj: Serializable): Unit = {
    toSocket.writeObject(obj)
    toSocket.flush()
  }


  def receiveCreatingMessage(a: CreatingMessage)
  def receiveBroadcastMessage(a: BroadcastingMessage)
}