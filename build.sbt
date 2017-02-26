import sbtassembly.PathList

organization := "com.olc.hadoop"

name := "WordCount"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  "ClouderaRepo" at "https://repository.cloudera.com/content/repositories/releases",
  "MavenHadoopRepo" at "https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-core",
  "MavenJettyRepo" at "https://mvnrepository.com/artifact/org.mortbay.jetty/jetty-util"
)

libraryDependencies ++= Seq(
  // https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-core
  "org.apache.hadoop" % "hadoop-core" % "0.20.2"
)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
    case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
    case "META-INF/mailcap" => MergeStrategy.last
    case "META-INF/mimetypes.default" => MergeStrategy.last
    case "plugin.properties" => MergeStrategy.last
    case "log4j.properties" => MergeStrategy.last
    case x => old(x)
  }
}

