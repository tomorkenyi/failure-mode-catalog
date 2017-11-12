name := """failure-mode-catalog"""
organization := "com.ge"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.2.12.Final",
  "mysql" % "mysql-connector-java" % "6.0.6",
  "org.mapstruct" % "mapstruct-jdk8" % "1.2.0.Final",
  "org.mapstruct" % "mapstruct-processor" % "1.2.0.Final",
  "org.projectlombok" % "lombok" % "1.16.18" % "provided"
)

// Since Play 2.4 the contents of the conf directory are added to the classpath by default.
// This option will disable that behavior and allow a JPA application to be deployed.
PlayKeys.externalizeResources := false