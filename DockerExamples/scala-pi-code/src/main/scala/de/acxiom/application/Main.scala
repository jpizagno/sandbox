package de.acxiom.application

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._

import scala.math._
import scala.util.Random

object MainApplication {
  
  def main(args: Array[String]) {
  
	  val numberSamples = 10000000
	  var i = 0
	  var sumAll = 0.0
	  var sumInner = 0.0
	  for (i <- 0 to numberSamples) {
	    sumAll += 1.0
	    val (x,y) = (Random.nextDouble(), Random.nextDouble())
	    if ( sqrt(x*x + y*y) < 1.0) {
	      sumInner += 1.0
	    }
	  }
	  
	  val pi = 4.0 * sumInner / sumAll
	  
	  System.out.println("pi = "+pi)
	  
  }
  
}
