name := "todo-backend-akka"

packageArchetype.java_application

scalaVersion := "2.11.6"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-testkit-experimental_2.11" % "1.0" % "test",
  "com.typesafe.akka" % "akka-persistence_2.11" % "2.4.0",
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "com.scalapenos" % "stamina-core_2.11" % "0.1.1-SNAPSHOT",
  "com.scalapenos" % "stamina-json_2.11" % "0.1.1-SNAPSHOT",
  "com.scalapenos" % "stamina-testkit_2.11" % "0.1.1-SNAPSHOT",
  "com.github.fommil" %% "spray-json-shapeless" % "1.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)

Revolver.settings
