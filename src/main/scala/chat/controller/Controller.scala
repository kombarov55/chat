package chat.controller

import javax.swing.JPanel

import akka.actor.{ActorRef, ActorSystem}
import chat.window.ChangePanel

/**
  * Created by nikolaykombarov on 20.08.17.
  */
trait Controller {

  val system = ActorSystem("mySystem")
  val window: ActorRef

  val panel: JPanel

  def becomeActive(): Unit = {
    window ! ChangePanel(panel)
  }

}
