name := "todo-backend-akka"

packageArchetype.java_application

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http-scala-experimental_2.11" % "1.0-RC2"
)
