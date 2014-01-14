logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// adds 'sbt gen-idea', which generats idea project files (for intelliJ)
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

// adds 'sbt dependency-tree', which generates a dependency tree
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.0")

// sbt assembly plugin
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.1")
