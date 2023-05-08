package es.jesusmtnez.ff.api

import io.circe.Error
import es.jesusmtnez.ff.*
import es.jesusmtnez.ff.data.*
import sttp.client3.*
import sttp.model.{Method, Uri}

trait About:
  def systemInfo: Response[
    Either[ResponseException[FireflyError, Error], FireflySingleResponse[
      SystemInfo
    ]]
  ]

object About:

  private val defaultBackend = HttpClientSyncBackend()

  def apply(host: Uri, auth: Auth): About =
    new About:
      override def systemInfo: Response[
        Either[ResponseException[FireflyError, Error], FireflySingleResponse[
          SystemInfo
        ]]
      ] =
        RequestConstructor
          .requestWithNoBody[SystemInfo, FireflyError](
            host,
            auth,
            Method.GET,
            "/api/v1/about"
          )
          .send(defaultBackend)
