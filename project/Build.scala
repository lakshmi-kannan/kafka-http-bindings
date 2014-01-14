import sbt._
import Keys._

import play.Project._
import sbtassembly.Plugin._
import AssemblyKeys._
import net.virtualvoid.sbt.graph.Plugin.graphSettings

object KafkaHttpBuild extends Build {

  val buildVersion      = "0.0.1"
  val buildScalaVersion = "2.10.3"

  val buildSettings = Seq (
    version      := buildVersion,
    scalaVersion := buildScalaVersion,
    scalacOptions := Seq("-deprecation", "-feature", "-unchecked")
  )

  lazy val root = Project(id = "kafka-http", base = file(".")).aggregate(api)

  lazy val api = play.Project("kafka-http-api",
    buildVersion,
    path = file("./api/")
  )
  .settings(buildSettings: _*)
}
