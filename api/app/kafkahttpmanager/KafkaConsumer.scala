package kafkahttpmanager

import akka.actor.{ActorLogging, Actor}
import kafkahttpmanager.ConsumeMessages.Consume
import kafka.consumer.{Whitelist, ConsumerConnector}
import kafka.serializer.DefaultDecoder

class KafkaConsumeHandler (conf: KafkaHttpConfig, consumer: ConsumerConnector) extends Actor with ActorLogging {

  def receive = {
    case Consume(topicId) =>
      // now do the blocking call.
      log.debug("Fetching data from kafka cluster for topic :'%s'".format())
      val stream  = consumer.createMessageStreamsByFilter(new Whitelist(topicId), 1, new DefaultDecoder(),
        new DefaultDecoder()).head

      for (item <- stream) {
        val key = new String(item.key)
        val message = new String(item.message)

        // send out the JSON response to sender
      }
  }
}