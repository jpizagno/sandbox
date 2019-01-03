package com.howtographql.scala.sangria

import sangria.schema.{Field, ListType, ObjectType}
import models._
// #
import sangria.schema._
import sangria.macros.derive._

object GraphQLSchema {

  implicit val LinkType = deriveObjectType[Unit, Link]()


  val Id = Argument("id", StringType)
  val Ids = Argument("ids", ListInputType(StringType))

  val QueryType = ObjectType(
    "Query",
    fields[MyContext, Unit](
      Field("allLinks", ListType(LinkType), resolve = c => c.ctx.dao.allLinks),
      Field("getNlatestLinks"
        , ListType(LinkType)
        , arguments = List(Argument("numLinks", IntType))
        , resolve = c => c.ctx.dao.getNlatestLinks(c.arg[Int]("numLinks")))
    )
  )

  val SchemaDefinition = Schema(QueryType)
}