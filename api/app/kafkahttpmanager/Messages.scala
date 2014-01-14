package kafkahttpmanager

object ConsumeMessages {
  // Messages for kafka consumption
  case class Consume(topicId: String)
  case class Error(topicId: String, error: Throwable)
}
