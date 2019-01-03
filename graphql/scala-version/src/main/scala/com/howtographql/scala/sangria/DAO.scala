package com.howtographql.scala.sangria

import DBSchema._
import com.howtographql.scala.sangria.models.Link
import com.mongodb.client.{FindIterable, MongoCollection}
import java.util.List
import java.util.ArrayList

import com.mongodb.BasicDBObject

import scala.concurrent.Future
import org.bson.Document


class DAO( links:MongoCollection[Document] ) {

  def allLinks():Seq[Link] =  {
    val allLinks: List[Link] = new ArrayList[Link]
    import scala.collection.JavaConversions._
    for (doc <- this.links.find) {
      allLinks.add(link(doc))
    }
    allLinks.toSeq
  }

  /**
    * Will retrieve the last N documents, in descinding order of Link.id
    *
    * @param numLinks The number of links to return
    * @return Java.util.List of Links
    */
  def getNlatestLinks(numLinks:Integer):Seq[Link] = {
    val sortObject:BasicDBObject = new BasicDBObject().append("_id", -1)
    val documentsSortedLimited:FindIterable[Document] = links.find().sort(sortObject).limit(numLinks)

    val linksSortedLimited: List[Link] = new ArrayList[Link]
    import scala.collection.JavaConversions._
    for (doc <- documentsSortedLimited) {
      linksSortedLimited.add(link(doc))
    }
    linksSortedLimited.toSeq
  }

  private def link(doc: Document) = new Link(doc.get("_id").toString, doc.getString("source"), doc.getString("url"), doc.getString("title"), doc.getString("text"))

}

//class DAO(db: Database) {
//  def allLinks = db.run(Links.result)
//  def getLink(id: Int): Future[Option[Link]] = db.run(
//    Links.filter(_.id === id).result.headOption
//  )
//  def getLinks(ids: Seq[Int]) = db.run(
//    Links.filter(_.id inSet ids).result
//  )
//}
