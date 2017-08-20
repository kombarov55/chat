package chat.net

sealed class Action extends Serializable

case class CreatingMessage(message: Message) extends Action with Serializable
case class BroadcastingMessage(message: Message) extends Action with Serializable