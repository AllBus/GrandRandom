package com.kos.grandrandom.randoms.blocks

import scalaxy.loops._
/**
  * Created by Kos on 16.10.2016.
  */
class ClockBlock extends MainBlock{
	override val MIN = 0x1f550
	override val MAX = 0x1f567

	override def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length=MAX-MIN+1
		for (i ← 1 to count optimized) {
			sb.appendAll {
				Character.toChars(MIN+random.nextInt(length))
			}
			if (i%3==0 && i!=count){
				sb.append(' ')
			}
		}
		sb.toString()
	}

	override def allValues= {
		val sb = new StringBuilder
		for (i ← MIN to MAX optimized) {
			sb.appendAll(Character.toChars(i))

		}
		sb.toString()
	}
}
