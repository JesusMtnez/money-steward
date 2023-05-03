package es.jesusmtnez.ff.model

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
      for {
        version <- c.downField("data").downField("version").as[String]
        apiVersion <- c.downField("data").downField("api_version").as[String]
        phpVersion <- c.downField("data").downField("php_version").as[String]
        os <- c.downField("data").downField("os").as[String]
        driver <- c.downField("data").downField("driver").as[String]
      } yield SystemInfo(version, apiVersion, phpVersion, os, driver)
  }
