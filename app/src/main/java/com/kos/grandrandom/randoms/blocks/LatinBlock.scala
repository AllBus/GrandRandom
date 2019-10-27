package com.kos.grandrandom.randoms.blocks

import scalaxy.loops._
/**
  * Created by Kos on 16.10.2016.
  */
class LatinBlock extends MainBlock{
	override val MIN = 0x0061
	override val MAX = 0x007a

	override def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length=MAX-MIN+1
		for (i ← 1 to count optimized) {
			sb.append {
				(MIN+random.nextInt(length)).toChar
			}

		}
		sb.toString()
	}
}
