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
  given Decoder[SystemInfo] = new Decoder[SystemInfo] {
    final def apply(c: HCursor): Decoder.Result[SystemInfo] =
      (
        c.downField("data").downField("version").as[String],
        c.downField("data").downField("api_version").as[String],
        c.downField("data").downField("php_version").as[String],
        c.downField("data").downField("os").as[String],
        c.downField("data").downField("driver").as[String]
      ).mapN(SystemInfo.apply)
  }
