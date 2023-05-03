package es.jesusmtnez.ff

import io.circe.Decoder
import sttp.client3.*
import sttp.client3.circe.*
import sttp.model.{Method, Uri}

object RequestConstructor {
  def runRequestWithNoBody[R: Decoder](
      auth: Auth,
      method: Method,
      path: Uri.PathSegments
  ) =
    val req = basicRequest
      .method(
        method,
        uri"http://firefly.home.es/api/".addPathSegments(path.segments)
      )
      .response(asJson[R])

    setAuth(auth)(req)

  private def setAuth[T, R](auth: Auth)(req: Request[T, R]): Request[T, R] =
    auth match {
      case Bearer(token) => req.auth.bearer(token)
    }
}
