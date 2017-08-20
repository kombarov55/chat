package chat

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}

/**
  * Created by nikolaykombarov on 19.08.17.
  */
object Implicits {

  implicit def Function2Runnable(fun: () => Unit): Runnable = new Runnable {
    override def run() = fun()
  }

  implicit def Function2ActionListener(fun: (ActionEvent) => Any): ActionListener = new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = fun(e)
  }

  implicit def Function2KeyListener(fun: (KeyEvent) => Unit): KeyAdapter = new KeyAdapter {
    override def keyPressed(e: KeyEvent): Unit = fun(e)
  }

}
