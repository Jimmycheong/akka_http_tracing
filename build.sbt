import Dependencies._ 

name := "akka_http_tracing"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
) ++ akkaHttpDeps ++ slickDeps