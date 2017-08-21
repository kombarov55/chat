package chat.swing

import java.awt.Dimension
import javax.swing.{JFrame, JPanel}

import akka.actor.Actor

/**
  * Created by nikolaykombarov on 19.08.17.
  */
class Window extends JFrame with Actor {


  override def receive: Receive = {
    case Setup() => setup()
    case ChangePanel(panel) => changePanel(panel)
  }

  def setup(): Unit = {
    setVisible(true)
    setPreferredSize(new Dimension(400, 200))

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  }

  var prevPanel: JPanel = null

  def changePanel(panel: JPanel): Unit = {
    if (prevPanel != null) remove(prevPanel)
    prevPanel = panel
    add(panel)
    pack()
  }

}

case class Setup()

case class ChangePanel(panel: JPanel)
