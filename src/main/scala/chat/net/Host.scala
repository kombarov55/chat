package chat.net

import java.net.ServerSocket

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import chat.Implicits.Function2Runnable

import scala.collection.mutable

class Host(port: Int) extends Actor {

  val serverSocket = new ServerSocket(port)

  val participants = mutable.Buffer[ActorRef]()

  override def receive: Receive = {
    case CreatingMessage(message) => broadcastMessage(BroadcastingMessage(message))
    case "start" => launchJoiner()
    case text => println(s"host received unknown message: $text")
  }

  def broadcastMessage(message: BroadcastingMessage) = {
    for (each <- participants) {
      each ! message
    }
  }

  def launchJoiner(): Unit = {
    new Thread({ () =>
      while (true) {
        val socket = serverSocket.accept()
        participants += context.actorOf(Props(new SocketWrapperOnServer(socket)), name = s"serverListener${participants.size}")
      }
    }).start()
  }
}
