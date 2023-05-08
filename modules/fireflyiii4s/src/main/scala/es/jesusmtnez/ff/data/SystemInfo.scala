package es.jesusmtnez.ff.data

import cats.syntax.all.*
import io.circe.*

final case class SystemInfo(
    version: String,
    apiVersion: String,
    phpVersion: String,
    os: String,
    driver: String
)

object SystemInfo:
  given Decoder[SystemInfo] = new Decoder[SystemInfo]:
    final def apply(c: HCursor): Decoder.Result[SystemInfo] =
      (
        c.downField("version").as[String],
        c.downField("api_version").as[String],
        c.downField("php_version").as[String],
        c.downField("os").as[String],
        c.downField("driver").as[String]
      ).mapN(SystemInfo.apply)
