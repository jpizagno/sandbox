package com.howtographql.scala.sangria

import slick.jdbc.H2Profile.api._

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.language.postfixOps
import com.howtographql.scala.sangria.models._
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase


object DBSchema {

  def createDatabase: DAO = {
    //val db = Database.forConfig("h2mem")
    //Await.result(db.run(databaseSetup), 10 seconds)

    val hostName:String = "127.0.0.1" // TODO: read from System.getEnv()
    val port:Integer = 27017 // TODO: read from System.getEnv()
    val database:String = "huggins_db" // TODO: read from System.getEnv()
    val collection:String = "links" // TODO: read from System.getEnv()
    val db:MongoDatabase = new MongoClient(hostName,port).getDatabase(database)

    new DAO(db.getCollection(collection)) //DAO(db)

  }

}
