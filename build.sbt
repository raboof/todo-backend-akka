name := "todo-backend-akka"

packageArchetype.java_application

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http-scala-experimental_2.11" % "1.0-RC2",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "1.0-RC2",
  "com.typesafe.akka" % "akka-http-testkit-scala-experimental_2.11" % "1.0-RC2" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)
