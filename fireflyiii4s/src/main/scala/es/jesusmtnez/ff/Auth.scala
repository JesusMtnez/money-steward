package es.jesusmtnez.ff

sealed trait Auth

final case class Bearer(token: String) extends Auth
