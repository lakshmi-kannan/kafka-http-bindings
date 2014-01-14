resolvers := Seq(
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "typesafe" at "http://repo.typesafe.com/typesafe/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.0.2",
  "com.typesafe.play" %% "play-json" % "2.2.1",
  "org.apache.kafka" % "kafka_2.10" % "0.8.0" exclude("com.sun.jmx","jmxri")
   exclude("com.sun.jdmk","jmxtools"),
   "com.wordnik" %% "swagger-play2" % "1.2.6-SNAPSHOT",
  // Testing
  "org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
  "org.mockito" % "mockito-all" % "1.8.5" % "test"
)
