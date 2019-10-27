package com.kos.grandrandom.randoms.blocks
import scalaxy.loops._
/**
  * Created by Kos on 16.10.2016.
  */
class PlantBlock extends ClockBlock{
	override val MIN = 0x1f330
	override val MAX = 0x1f342

	val DIV = 0x1f336
	val DIV_ADD = 1


	override def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length=MAX-MIN+1-DIV_ADD
		for (i ← 1 to count optimized) {
			sb.appendAll {
				val a=MIN+random.nextInt(length)
				Character.toChars(if (a>=DIV) a+DIV_ADD	else a)
			}
			if (i%3==0 && i!=count){
				sb.append(' ')
			}
		}
		sb.toString()
	}

	override def allValues= {
		val sb = new StringBuilder
		for (a ← MIN to MAX-DIV_ADD) {
			sb.appendAll(Character.toChars(if (a>=DIV) a+DIV_ADD else a))
		}
		sb.toString()
	}

}
