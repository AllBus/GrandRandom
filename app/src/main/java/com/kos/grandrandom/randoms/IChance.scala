package com.kos.grandrandom.randoms

import scalaxy.streams._

/**
  * Created by Kos on 16.10.2016.
  */
trait IChance {

	def generate():String
	def generate(count:Int):String
	def generateShots(count:Int,shots:Int):Seq[String]={
		optimize {for (_ ← 1 to shots) yield generate(count)
		}
	}

	override def toString: String = {
		getClass.getSimpleName
	}

	/**
	  *
	  * @return Можно ли задать диапазон значений
	  */
	def hasDiapazon = false

	/**
	  * Пропуск некоторого числа случайных значений
	  * @param offset
	  */
	def offset(offset:Long)

	def allValues :String
}
