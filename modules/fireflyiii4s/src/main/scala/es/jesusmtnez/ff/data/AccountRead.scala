package es.jesusmtnez.ff.data

import cats.syntax.all.*
import io.circe.*

final case class AccountRead(
    `type`: String,
    id: String,
    attributes: Account
)

object AccountRead:
  given Decoder[AccountRead] = new Decoder[AccountRead]:
    final def apply(c: HCursor): Decoder.Result[AccountRead] =
      (
        c.downField("type").as[String],
        c.downField("id").as[String],
        c.downField("attributes").as[Account]
      ).mapN(AccountRead.apply)
