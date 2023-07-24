package es.jesusmtnez.ff.data

import cats.syntax.all.*
import io.circe.*

final case class Pagination(
    total: Int,
    count: Int,
    perPage: Int,
    currentPage: Int,
    totalPages: Int
)

object Pagination:
  given Decoder[Pagination] = new Decoder[Pagination]:
    final def apply(c: HCursor): Decoder.Result[Pagination] =
      (
        c.downField("total").as[Int],
        c.downField("count").as[Int],
        c.downField("per_page").as[Int],
        c.downField("current_page").as[Int],
        c.downField("total_pages").as[Int]
      ).mapN(Pagination.apply)

final case class Meta(
    pagination: Pagination
)

object Meta:
  given Decoder[Meta] = new Decoder[Meta]:
    final def apply(c: HCursor): Decoder.Result[Meta] =
      c.downField("pagination").as[Pagination].map(Meta(_))
