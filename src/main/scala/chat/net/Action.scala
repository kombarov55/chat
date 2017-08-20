package chat.net

sealed class Action extends Serializable

case class NameRequest() extends Action with Serializable
case class NameResponse(name: String) extends Action with Serializable

case class CreatingMessage(message: Message) extends Action with Serializable
case class BroadcastingMessage(message: Message) extends Action with Serializable