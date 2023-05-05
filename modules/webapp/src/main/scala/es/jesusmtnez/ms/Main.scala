package es.jesusmtnez.ms

import cats.effect.IO
import tyrian.Html.*
import tyrian.*

import scala.scalajs.js.annotation.*

@JSExportTopLevel("TyrianApp")
object Main extends TyrianApp[Msg, Model]:

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) = (0, Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case Msg.Increment => (model + 1, Cmd.None)
    case Msg.Decrement => (model - 1, Cmd.None)

  def view(model: Model): Html[Msg] =
    div(`class` := "container text-center")(
      div(`class` := "row align-item-start")(
        div(`class` := "col")(
          button(
            `type` := "button",
            `class` := "btn btn-dark",
            onClick(Msg.Decrement)
          )(text("-"))
        ),
        div(`class` := "col")(
          text(model.toString)
        ),
        div(`class` := "col")(
          button(
            `type` := "button",
            `class` := "btn btn-dark",
            onClick(Msg.Increment)
          )(text("+"))
        )
      )
    )

  def subscriptions(model: Model): Sub[IO, Msg] =
    Sub.None

type Model = Int

enum Msg:
  case Increment, Decrement
