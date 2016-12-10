name := "todo-backend-akka"

packageArchetype.java_application

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature", "-language:postfixOps")

val akkaHttpVersion = "10.0.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http_2.11" % akkaHttpVersion,
  "com.typesafe.akka" % "akka-http-spray-json_2.11" % akkaHttpVersion,
  "com.typesafe.akka" % "akka-http-testkit_2.11" % akkaHttpVersion % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

Revolver.settings
