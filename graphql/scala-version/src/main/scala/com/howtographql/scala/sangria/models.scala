package com.howtographql.scala.sangria

package object models {

  case class Link(id: String, source:String, url: String, title:String, text: String)

  case class MyContext(dao: DAO) {

  }

}
