package kafkahttpmanager

import akka.actor._
import akka.routing.ConsistentHashingRouter
import akka.util.Timeout
import java.util.concurrent.TimeUnit
import kafkahttpmanager.ConsumeMessages._
import akka.routing.ConsistentHashingRouter.ConsistentHashMapper
import kafka.consumer.Consumer

class KafkaHttpManager(conf: KafkaHttpConfig) {
  implicit val timeout = Timeout(10, TimeUnit.SECONDS)
  val consumer = Consumer.create(conf.getConsumerConfig())

  var system: ActorSystem = _
  var consume_router: ActorRef = _

  def stop () = {
    system.stop(consume_router)
  }

  def start (sys: Option[ActorSystem]) = {
    system = sys getOrElse ActorSystem("KafkaHttpServer")


    val workers = Vector.fill(conf.maxConsumerActors) {
      system.actorOf(Props(new KafkaConsumeHandler(conf, consumer)))
    }

    consume_router = system.actorOf(Props.empty.withRouter(
      ConsistentHashingRouter(workers).withHashMapper(new KafkaConsumerHashMapper)))
  }
}

class KafkaConsumerHashMapper extends ConsistentHashMapper {
  def hashKey(message: Any): Any = {
    message match {
      case message: Consume => message.topicId
      case _ => super.hashKey(message)
    }
  }
}

