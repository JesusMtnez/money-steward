package es.jesusmtnez.ff.data

import cats.syntax.functor.*
import io.circe.Decoder
import io.circe.generic.auto._
import io.circe.syntax.*

sealed trait FireflyError
final case class ValidationError(message: String)
    extends FireflyError // TODO Include errors
final case class GeneralError(message: String, exception: String)
    extends FireflyError

object FireflyError:
  given Decoder[FireflyError] = List[Decoder[FireflyError]](
    Decoder[GeneralError].widen,
    Decoder[ValidationError].widen
  ).reduceLeft(_ or _)
