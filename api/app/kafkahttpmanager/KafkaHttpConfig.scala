package kafkahttpmanager

import play.api.Configuration
import play.Logger
import java.util.Properties
import kafka.consumer.ConsumerConfig

class KafkaHttpConfig (conf: Configuration) {
  private val rootKey = "acumen"

  def getString(key: String): String = {
    try {
      conf.getString("%s.%s".format(rootKey, key)).get
    } catch {
      case e: Exception =>
        Logger.error("missing/malformed configuration key %s - %s".format(key, e.getMessage))
        System.exit(1)
        null
    }
  }

  def getStringList(key: String): List[String] = {
    try {
      conf.getStringList("%s.%s".format(rootKey, key)).get.toList
    } catch {
      case e: Exception =>
        Logger.error("missing/malformed configuration key %s - %s".format(key, e.getMessage))
        System.exit(1)
        null
    }
  }

  def getInt(key: String): Int = {
    try {
      conf.getInt("%s.%s".format(rootKey, key)).get
    } catch {
      case e: Exception =>
        Logger.error("missing/malformed configuration key %s - %s".format(key, e.getMessage))
        System.exit(1)
        0
    }
  }

  def getBoolean(key: String): Boolean = {
    try {
      conf.getBoolean("%s.%s".format(rootKey, key)).get
    } catch {
      case e: Exception =>
        Logger.error("missing/malformed configuration key %s - %s".format(key, e.getMessage))
        System.exit(1)
        false
    }
  }

  def getConsumerConfig(): ConsumerConfig = {
    val props = new Properties()
    props.put("zookeeper.connect", getString("kafka.zookeeper.url"))
    props.put("group.id", getString("kafka.consumer.groupId"))
    props.put("zookeeper.session.timeout.ms", "400")
    props.put("zookeeper.sync.time.ms", "200")
    props.put("auto.commit.interval.ms", "1000")
    props.put("auto.offset.reset", "largest")
    return new ConsumerConfig(props)
  }

  // Consumer config
  val maxConsumerActors = getInt("kafka.consumer.actors")
}
