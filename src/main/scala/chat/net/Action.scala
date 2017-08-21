package chat.net

case class CreatingMessage(message: Message) extends Serializable
case class BroadcastingMessage(message: Message) extends Serializable