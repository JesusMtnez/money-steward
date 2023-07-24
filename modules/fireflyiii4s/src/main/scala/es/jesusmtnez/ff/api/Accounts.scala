package es.jesusmtnez.ff.api

import io.circe.Error
import es.jesusmtnez.ff.*
import es.jesusmtnez.ff.data.*
import sttp.client3.*
import sttp.model.{Method, Uri}

trait Accounts:
  def getAccount(id: String): Response[
    Either[ResponseException[FireflyError, Error], FireflySingleResponse[
      Account
    ]]
  ]

  def getAccounts(): Response[
    Either[ResponseException[FireflyError, Error], FireflyPaginatedResponse[
      AccountRead
    ]]
  ]

object Accounts:
  private val defaultBackend = HttpClientSyncBackend()

  def apply(host: Uri, auth: Auth): Accounts =
    new Accounts:
      override def getAccount(id: String): Response[
        Either[ResponseException[FireflyError, Error], FireflySingleResponse[
          Account
        ]]
      ] =
        RequestConstructor
          .requestWithNoBody[FireflySingleResponse[Account], FireflyError](
            host,
            auth,
            Method.GET,
            s"/api/v1/accounts/${id}"
          )
          .send(defaultBackend)
      override def getAccounts(): Response[
        Either[ResponseException[FireflyError, Error], FireflyPaginatedResponse[
          AccountRead
        ]]
      ] =
        RequestConstructor
          .requestWithNoBody[FireflyPaginatedResponse[
            AccountRead
          ], FireflyError](
            host,
            auth,
            Method.GET,
            s"/api/v1/accounts",
            Map("type" -> "asset")
          )
          .send(defaultBackend)
