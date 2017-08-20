package chat.net

import akka.actor.ActorSystem

/**
  * Created by nikolaykombarov on 20.08.17.
  */
object Client {

  val system = ActorSystem("mySystem")

  var playerName = ""
  var serverAddress = ""

}