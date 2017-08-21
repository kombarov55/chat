package chat

import java.net.InetAddress

import akka.actor.Props
import chat.net.{Client, Host}

/**
  * Created by nikolaykombarov on 20.08.17.
  */
object LaunchServer extends App {

  val host = Client.system.actorOf(Props(new Host(6666)), name = "host")
  host ! "start"
  val address = InetAddress.getLocalHost.getHostAddress
  val port = 6666
  println(s"Сервер запущен. Адрес: $address:$port")
}
