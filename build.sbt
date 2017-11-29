name := """failure-mode-catalog"""
organization := "com.ge"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  "org.mapstruct" % "mapstruct-jdk8" % "1.2.0.Final",
  "org.mapstruct" % "mapstruct-processor" % "1.2.0.Final",
  "org.projectlombok" % "lombok" % "1.16.18" % "provided",
  "org.assertj" % "assertj-core" % "3.8.0" % "test",
  "org.mongodb" % "mongodb-driver-rx" % "1.5.0",
  "org.mongodb.morphia" % "morphia" % "1.3.2"
)

// Since Play 2.4 the contents of the conf directory are added to the classpath by default.
// This option will disable that behavior and allow a JPA application to be deployed.
PlayKeys.externalizeResources := false