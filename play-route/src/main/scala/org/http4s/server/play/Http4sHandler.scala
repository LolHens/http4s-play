package org.http4s.server.play

import akka.stream.scaladsl.Sink
import akka.util.ByteString
import cats.effect.ConcurrentEffect
import cats.syntax.all._
import org.http4s.{Method, Response, _}
import play.api.http.HttpEntity.Streamed
import play.api.libs.streams.Accumulator
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds

object Http4sHandler {
  def apply[F[_] : ConcurrentEffect](httpApp: HttpApp[F])
                                    (implicit executionContext: ExecutionContext): EssentialAction =
    EssentialAction { requestHeader =>
      playRequestToPlayResponse(httpApp)(
        requestHeader,
        Method.fromString(requestHeader.method).right.get
      )
    }


  type PlayAccumulator = Accumulator[ByteString, Result]

  /**
    * A Play accumulator Sinks HTTP data in, and then pumps out a future of a Result.
    * That Result will have a Source as the response HTTP Entity.
    *
    * Here we create a unattached sink, map its materialized value into a publisher,
    * convert that into an FS2 Stream, then pipe the request body into the http4s request.
    */
  private def playRequestToPlayResponse[F[_] : ConcurrentEffect](httpApp: HttpApp[F])
                                                                (requestHeader: RequestHeader, method: Method)
                                                                (implicit executionContext: ExecutionContext): PlayAccumulator = {
    val sink: Sink[ByteString, Future[Result]] =
      byteStreamSink[F, Future[Result]] { requestBodyStream =>
        val http4sRequest =
          requestHeaderToRequest(requestHeader, method)
            .withBodyStream(requestBodyStream)

        val wrappedResponse: F[Response[F]] = httpApp(http4sRequest)

        val wrappedResult: F[Result] = wrappedResponse.map { response =>
          Result(
            header = responseToResponseHeader(response),
            body = Streamed(
              data = byteStreamSource(response.body),
              contentLength = response.contentLength,
              contentType = response.contentType.map(_.value)
            )
          )
        }

        effectToFuture[F, Result](wrappedResult)
      }

    Accumulator(sink)
  }
}
