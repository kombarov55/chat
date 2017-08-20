import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

import chat.Implicits.Function2Runnable
import chat.net.Message

/**
  * Created by nikolaykombarov on 19.08.17.
  */
class SendingObjects {

  def testSendingObjects(): Unit = {

    val server = new ServerSocket(6667)

    val serverPart = new Thread({ () =>

      val serverToClient = server.accept()
      val streamToClient = new ObjectOutputStream(serverToClient.getOutputStream)
      val streamFromClient = new ObjectInputStream(serverToClient.getInputStream)
      println("server: Initialized ")


      while (true) {
        val msg = streamFromClient.readObject()
        val msgType = msg match {
          case Message(_, _, _) => "message"
          case _ => "other"
        }
        println(s"received message: its a $msgType")
      }

    }).start()


    val clientPart = new Thread({ () =>

      val clientToServer = new Socket("localhost", 6667)
      val streamToServer = new ObjectOutputStream(clientToServer.getOutputStream)
      val streamFromServer = new ObjectInputStream(clientToServer.getInputStream)
      println("client: Initialized ")

      val obj = Message("Nikolay", "hey there!!!!!")

      for (i <- 1 to 10) {
        streamToServer.writeObject(obj)
        streamToServer.flush()

        Thread.sleep(1000)
      }

    }).start()

  }

}
