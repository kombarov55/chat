package chat.net

import java.time.LocalTime

/**
  * Created by nikolaykombarov on 19.08.17.
  */
case class Message(author: String, text: String, creationTime: LocalTime = LocalTime.now()) extends Serializable
