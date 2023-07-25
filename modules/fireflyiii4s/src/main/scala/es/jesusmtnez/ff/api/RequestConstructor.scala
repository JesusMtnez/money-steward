package es.jesusmtnez.ff.api

import es.jesusmtnez.ff.*
import es.jesusmtnez.ff.data.*
import io.circe.Decoder
import sttp.client3.*
import sttp.client3.circe.*
import sttp.model.*

object RequestConstructor {
  def requestWithNoBody[R: Decoder, E: Decoder](
      host: Uri,
      auth: Auth,
      method: Method,
      endpoint: String,
      parameters: Map[String, String] = Map.empty
  ) =
    val req = basicRequest
      .method(
        method,
        host.withWholePath(endpoint).withParams(parameters)
      )
      .header(
        Header("User-Agent", "fireflyiii.scala/0.0.1"),
        replaceExisting = true
      )
      .response(asJsonEither[E, R])

    setAuth(auth)(req)

  private def setAuth[T, R](auth: Auth)(req: Request[T, R]): Request[T, R] =
    auth match {
      case Bearer(token) => req.auth.bearer(token)
    }
}
