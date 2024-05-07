name := "ScalaDiplome"
val sharedSettings = List(
  version := "0.1",
  scalaVersion := "2.11.5"
)

lazy val db = Project(id = "db", base = file("modules/db"))
  .settings(sharedSettings :_*)
  .settings(
    libraryDependencies += "com.typesafe" % "config" % "1.4.3",
    libraryDependencies ++= Seq(
      "org.liquibase" % "liquibase-core" % "3.4.2",
      "org.squeryl" %% "squeryl" % "0.9.5-7",
      "com.zaxxer" % "HikariCP" % "4.0.1",
      "org.postgresql" % "postgresql" % "42.3.1"
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3"
    )
  )

lazy val di = Project(id = "di", base = file("modules/di"))
  .settings(sharedSettings :_*)
  .settings(libraryDependencies += Dependencies.Guice)

lazy val hnapi = Project(id = "hnapi", base = file("modules/hnapi"))
  .dependsOn(di, db)
  .settings(sharedSettings :_*)
  .enablePlugins(PlayScala)

lazy val root = (project in file("."))
  .settings(sharedSettings :_*)
  .dependsOn(hnapi)
  .aggregate(hnapi, di)
  .enablePlugins(PlayScala)