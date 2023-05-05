import org.scalajs.linker.interface.ModuleSplitStyle

val scalaV = "3.2.2"

ThisBuild / organization := "es.jesusmtnez"
ThisBuild / organizationName := "JesusMtnez"
ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.MIT)
ThisBuild / scalaVersion := scalaV

val catsV = "2.9.0"
val catsEffectV = "3.4.10"
val munitV = "0.7.29"
val munitCE3V = "1.0.7"
val sttpV = "3.8.15"

lazy val fireflyiii4s = project
  .in(file("modules/fireflyiii4s"))
  .settings(
    name := "fireflyiii4s",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %% "core" % sttpV,
      "com.softwaremill.sttp.client3" %% "circe" % sttpV,
      "io.circe" %% "circe-generic" % "0.14.5"
    )
  )

lazy val webapp = project
  .in(file("modules/webapp"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "money-steward-webapp",
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallestModules)
    },
    libraryDependencies ++= Seq(
      "io.indigoengine" %%% "tyrian-io" % "0.6.2",
      "org.scala-js" %%% "scalajs-dom" % "2.4.0"
    )
  )

lazy val core = project
  .in(file("modules/core"))
  .settings(
    name := "money-steward",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % catsV,
      "org.typelevel" %% "cats-effect" % catsEffectV,
      "org.scalameta" %% "munit" % munitV % Test,
      "org.typelevel" %% "munit-cats-effect-3" % munitCE3V % Test
    )
  )
  .enablePlugins(BuildInfoPlugin)

lazy val root = project
  .in(file("."))
  .settings(publish / skip := true)
  .aggregate(
    fireflyiii4s,
    core
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt Test/scalafmt")
