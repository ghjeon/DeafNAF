name := "DeafNAF"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.2",
  "org.reactivemongo" %% "reactivemongo" % "0.10.0",
  "com.google.android.gcm" % "gcm-server" % "1.0.2"
)

resolvers ++= Seq("spray" at "http://repo.spray.io/",
"Spy Repository" at "http://files.couchbase.com/maven2",
"GCM Server Repository" at "https://raw.github.com/slorber/gcm-server-repository/master/releases/")

play.Project.playScalaSettings
