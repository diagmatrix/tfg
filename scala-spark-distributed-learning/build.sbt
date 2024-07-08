ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.0"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "1.0.1"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided

scalacOptions += "-Xasync"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.4.0",
  "org.apache.spark" %% "spark-sql" % "3.4.0",
  "org.antlr" % "antlr4-runtime" % "4.13.1",
  "org.antlr" % "stringtemplate" % "3.2",
)

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "1.0.0-M1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-spark-distributed-learning"
  )