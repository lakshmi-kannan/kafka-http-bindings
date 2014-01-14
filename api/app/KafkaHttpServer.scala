import kafkahttpmanager.{KafkaHttpConfig, KafkaHttpManager}
import play.api._
import play.api.libs.concurrent.Akka
import play.api.Play.current
import play.Logger


package object App {
  val httpAppConfig = new KafkaHttpConfig(play.api.Play.application.configuration)
  lazy val kafkaHttpManager = new KafkaHttpManager(App.httpAppConfig)
}

object KafkaHttpServer extends GlobalSettings {

  override def onStart(app: Application) {
    App.kafkaHttpManager.start(sys = Option(Akka.system))
    Logger.info("Kafka HTTP server has started")
  }

  override def onStop(app: Application) {
    Logger.info("Kafka HTTP server is being shutdown.")
    App.kafkaHttpManager.stop()
  }
}
