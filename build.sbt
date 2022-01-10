ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.1.0"

val zioV = "1.0.13"
val squantsV = "1.8.3"

lazy val core = (project in file("core"))
  .settings(
    name := "money-steward",
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio"     % zioV,
      "org.typelevel" %% "squants" % squantsV
    )
  )
