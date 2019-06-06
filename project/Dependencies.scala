import sbt._

object Dependencies {
  import Versions._

  val akkaHttpDeps = Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-caching"    % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"          % "2.5.19"
  )

  val slickDeps = Seq(
    "com.typesafe"        % "config"                % "1.3.4",
    "com.typesafe.slick"  %% "slick"                % "3.3.0",
    "com.typesafe.slick"  %% "slick-hikaricp"       % "3.3.0",
    "org.postgresql"      % "postgresql"            % "42.2.5",
    "org.slf4j"           % "slf4j-nop"             % "1.6.4"
  )
}

object Versions {
  val akkaHttpVersion = "10.1.8"
}