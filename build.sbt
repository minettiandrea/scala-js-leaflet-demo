import Dependencies._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ch.wavein"


lazy val runDemo = taskKey[Unit]("runDemo")

lazy val root = (project in file("."))
  .settings(
    name := "scala-js-leaflet-demo",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "ch.wavein" %%% "scala-js-leaflet" % "0.1.0-SNAPSHOT",
    libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.6.7",
    libraryDependencies += "fr.iscpif.scaladget" %%% "bootstrapnative" % "1.2.3",

      runDemo := {
          val demoTarget = target.value
          val demoResource = (resourceDirectory in Compile).value

          val demoJS = (fullOptJS in Compile).value

          IO.copyFile(demoJS.data, demoTarget / "js/demo.js")
          IO.copyFile(demoJS.data.getParentFile / (demoJS.data.getName + ".map"), demoTarget / "js/demo.js.map")
          IO.copyFile(dependencyFile.value, demoTarget / "js/deps.js")

          IO.copyFile(demoResource / "leaflet-demo.html", demoTarget / "leaflet-demo.html")
          IO.copyDirectory(demoResource / "js", demoTarget / "js")
          IO.copyDirectory(demoResource / "css", demoTarget / "css")
          IO.copyDirectory(demoResource / "data", demoTarget / "data")
      }

  ).enablePlugins(ExecNpmPlugin)

// Uncomment the following for publishing to Sonatype.
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

// ThisBuild / description := "Some descripiton about your project."
// ThisBuild / licenses    := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
// ThisBuild / homepage    := Some(url("https://github.com/example/project"))
// ThisBuild / scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/your-account/your-project"),
//     "scm:git@github.com:your-account/your-project.git"
//   )
// )
// ThisBuild / developers := List(
//   Developer(
//     id    = "Your identifier",
//     name  = "Your Name",
//     email = "your@email",
//     url   = url("http://your.url")
//   )
// )
// ThisBuild / pomIncludeRepository := { _ => false }
// ThisBuild / publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
//   else Some("releases" at nexus + "service/local/staging/deploy/maven2")
// }
// ThisBuild / publishMavenStyle := true
