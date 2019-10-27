package com.kos.grandrandom.randoms

import scala.util.Random
import scalaxy.loops._

/**
  * Created by Kos on 16.10.2016.
  */
class Orel extends IChance{

	val random = new Random()

	def generate(): String = {
		generate(1)
	}

	def generate(count: Int): String = {
		val sb = new StringBuilder()
		for (i ← 1 to count optimized) {
			sb.append {
				if (random.nextBoolean()) '①' else 'ⓞ'
			}
			if (i%3==0 && i!=count){
				sb.append(' ')
			}
		}

		sb.toString()
	}
	override def allValues="①ⓞ"

	/**
	  * Пропуск некоторого числа случайных значений
	  *
	  * @param offset
	  */
	override def offset(offset: Long): Unit = {
		val k=(offset%1000).toInt
		for (i ← 1 to k optimized)
			random.nextLong()
	}
}
