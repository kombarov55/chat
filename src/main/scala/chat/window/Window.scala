package chat.window

import java.awt.Dimension
import javax.swing.JFrame

import akka.actor.{Actor, ActorRef}
import chat.controller.Controller

/**
  * Created by nikolaykombarov on 19.08.17.
  */
object Window extends JFrame {

  setVisible(true)
  setPreferredSize(new Dimension(400, 200))

  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  var activeController: ActorRef = null

  def setActiveController(actorRef: ActorRef): Unit = {
    if (activeController != null) activeController ! "clear"

    activeController = actorRef
    activeController ! "setup"
  }
}
