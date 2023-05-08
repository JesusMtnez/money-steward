package es.jesusmtnez.ff.data

import cats.syntax.all.*
import io.circe.*

sealed trait FireflyResponse

case class FireflySingleResponse[A](data: A) extends FireflyResponse
object FireflySingleResponse:
  implicit def decoder[A: Decoder]: Decoder[FireflySingleResponse[A]] =
    new Decoder[FireflySingleResponse[A]] {
      final def apply(c: HCursor): Decoder.Result[FireflySingleResponse[A]] =
        c.downField("data").as[A].map(FireflySingleResponse(_))
    }

// case class FireflyPaginatedResponse[A: Decoder](data: A, meta: Json) extends FireflyResponse
