name := """failure-mode-catalog"""
organization := "com.ge"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.2.12.Final",
  "com.h2database" % "h2" % "1.4.196",
  "mysql" % "mysql-connector-java" % "6.0.6"
)

// Since Play 2.4 the contents of the conf directory are added to the classpath by default.
// This option will disable that behavior and allow a JPA application to be deployed.
PlayKeys.externalizeResources := false