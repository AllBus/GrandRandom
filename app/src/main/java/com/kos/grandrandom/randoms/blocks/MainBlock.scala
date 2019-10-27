package com.kos.grandrandom.randoms.blocks



import com.kos.grandrandom.randoms.IChance

import scala.util.Random
import scalaxy.loops._

/**
  * Created by Kos on 16.10.2016.
  */
class MainBlock extends IChance{

	val random = new Random(System.currentTimeMillis())
	val MAX=0x257F
	val MIN=0x2500

	def generate(): String = {
		generate(1)
	}

	def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length=MAX-MIN+1
		for (i ← 1 to count optimized) {
			sb.append {
				(MIN+random.nextInt(length)).toChar
			}
			if (i%3==0 && i!=count){
				sb.append(' ')
			}
		}
		sb.toString()
	}

	override def allValues= {
		val sb = new StringBuilder
		for (i ← MIN to MAX) {
			sb.append(i.toChar)

		}
		sb.toString()
	}

	override def offset(offset: Long): Unit = {
		val k=(offset%1000).toInt
		for (i ← 1 to k optimized)
			random.nextLong()
	}
}
