package es.jesusmtnez.ff.endpoints

import io.circe.Error
import es.jesusmtnez.ff.*
import es.jesusmtnez.ff.data.SystemInfo
import sttp.client3.*
import sttp.model.Method

object About:

  def systemInfo(
      auth: Bearer
  ): Request[Either[ResponseException[String, Error], SystemInfo], Any] =
    RequestConstructor
      .runRequestWithNoBody[SystemInfo](
        auth,
        Method.GET,
        uri"/v1/about".pathSegments
      )
