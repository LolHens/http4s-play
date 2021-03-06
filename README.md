# http4s-play
[![Test Workflow](https://github.com/LolHens/http4s-play/workflows/test/badge.svg)](https://github.com/LolHens/http4s-play/actions?query=workflow%3Atest)
[![Release Notes](https://img.shields.io/github/release/LolHens/http4s-play.svg?maxAge=3600)](https://github.com/LolHens/http4s-play/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/de.lolhens/http4s-play_2.13)](https://search.maven.org/artifact/de.lolhens/http4s-play_2.13)
[![Apache License 2.0](https://img.shields.io/github/license/LolHens/http4s-play.svg?maxAge=3600)](https://www.apache.org/licenses/LICENSE-2.0)
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)

Use [http4s](https://github.com/http4s/http4s) routes as [Play framework routes](https://github.com/playframework/playframework/blob/master/core/play/src/main/scala/play/api/routing/Router.scala).

What's the benefit? To benefit from the nice features that both Play and http4s offer, without drastic nearly-impossible migrations. Play can still be the outside, while you use http4s for the nice fully pure FP.

See https://github.com/http4s/http4s/pull/1819/ for more background.

### build.sbt
```sbt
libraryDependencies += "de.lolhens" %% "http4s-play" % "0.0.2"
```

## Usage

Run the tests:
```
$ sbt play-route/test
```
Running:
```
$ sbt example/run
```

## License
This project uses the Apache 2.0 License. See the file called LICENSE.
