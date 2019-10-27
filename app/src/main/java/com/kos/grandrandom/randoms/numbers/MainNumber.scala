package com.kos.grandrandom.randoms.numbers



import com.kos.grandrandom.randoms.IChance

import scala.util.Random
import scalaxy.streams._
import scalaxy.loops._
/**
  * Created by Kos on 16.10.2016.
  */
class MainNumber(var min: Int = 1, var max: Int = 99) extends IChance {

	val random = new Random(System.currentTimeMillis())


	def generate(): String = {
		generate(1)
	}

	def generate(count: Int): String = {

		val length = max - min + 1

		if (length > 0) {
			optimize {
				(for (i ← 0 until count) yield min + random.nextInt(length)).grouped(3).map(_.mkString(" ")).mkString("\n")
			}
		} else
			""
	}

	override def hasDiapazon=true

	override def allValues ={
		if (min<=max){
			max - min match {
				case 0 ⇒ min.toString
				case 1 ⇒ min.toString+", "+max.toString
				case 2 ⇒ min.toString+", "+(min+1).toString+", "+max.toString
				case 3 ⇒ min.toString+", "+(min+1).toString+", "+(min+2).toString+", "+max.toString
				case _ ⇒ min.toString+", "+(min+1).toString+", ... , "+max.toString
			}

		}else
			""
	}
	override def offset(offset: Long): Unit = {
		val k=(offset%1000).toInt
		for (i ← 1 to k optimized)
			random.nextLong()
	}
}
