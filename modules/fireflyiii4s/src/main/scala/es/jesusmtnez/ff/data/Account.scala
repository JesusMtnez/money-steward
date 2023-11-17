package es.jesusmtnez.ff.data

import cats.syntax.all.*
import io.circe.*

import java.time.*

final case class Account(
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
    active: Option[Boolean],
    // order: Option[Int],
    name: String,
    `type`: String,              // TODO: ADT
    accountRole: Option[String], // TODO ADT
    currencyId: Option[String],  // TODO Newtype
    currencyCode: Option[String],
    currencySymbol: Option[String],
    currencyDecimalPlaces: Option[Int],
    currencyBalance: Option[String],
    currencyBalanceDate: Option[LocalDate],
    iban: Option[String],          // TODO Newtype
    bic: Option[String],           // TODO Newtype
    accountNumber: Option[String], // TODO Newtype
    // openingBalance: String,
    // openingBalanceDate: Option[ZonedDateTime],
    // currentDebt: Option[String],
    // virtualBalance: Option[String],
    // includeNetWorth: Option[Boolean],
    // creditCardType: Option[String], // TODO ADT CreditCardType
    // monthlyPaymentDate: Option[ZonedDateTime],
    // liabilityType: Option[String], // TODO Adt LiabilityType
    // liabilityDirection: Option[String], // TODO ADT LiabilityDirection
    // interest: Option[String],
    // interestPeriod: Option[String], // TODO ADT InterestPeriod
    notes: Option[String]
)

object Account:
  given Decoder[Account] = new Decoder[Account]:
    final def apply(c: HCursor): Decoder.Result[Account] =
      (
        c.downField("created_at").as[ZonedDateTime],
        c.downField("updated_at").as[ZonedDateTime],
        c.downField("active").as[Option[Boolean]],
        // c.downField("order").as[Option[Int]],
        c.downField("name").as[String],
        c.downField("type").as[String],
        c.downField("accountRole").as[Option[String]],
        c.downField("currencyId").as[Option[String]],
        c.downField("currencyCode").as[Option[String]],
        c.downField("currencySymbol").as[Option[String]],
        c.downField("currencyDecimalPlaces").as[Option[Int]],
        c.downField("currencyBalance").as[Option[String]],
        c.downField("currencyBalanceDate").as[Option[LocalDate]],
        c.downField("iban").as[Option[String]],
        c.downField("bic").as[Option[String]],
        c.downField("accountNumber").as[Option[String]],
        // c.downField("openingBalance").as[String],
        // c.downField("openingBalanceDate").as[Option[ZonedDateTime]],
        // c.downField("currentDebt").as[Option[String]],
        // c.downField("virtualBalance").as[Option[String]],
        // c.downField("includeNetWorth").as[Option[Boolean]],
        // c.downField("creditCardType").as[Option[String]],
        // c.downField("monthlyPaymentDate").as[Option[ZonedDateTime]],
        // c.downField("liabilityType").as[Option[String]],
        // c.downField("liabilityDirection").as[Option[String]],
        // c.downField("interest").as[Option[String]],
        // c.downField("interestPeriod").as[Option[String]],
        c.downField("notes").as[Option[String]]
      ).mapN(Account.apply)
