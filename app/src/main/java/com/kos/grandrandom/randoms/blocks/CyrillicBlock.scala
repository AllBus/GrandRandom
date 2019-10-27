package com.kos.grandrandom.randoms.blocks

import scalaxy.loops._

/**
  * Created by Kos on 16.10.2016.
  */
class CyrillicBlock extends MainBlock{
	override val MIN = 0x0430
	override val MAX = 0x0450

	override def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length=MAX-MIN+1
		for (i ← 0 until count optimized) {
			sb.append {
				val a=MIN+random.nextInt(length)
				if (a==MAX)
					'ё'
				else
					a.toChar
			}
		}
		sb.toString()
	}

	override def allValues= {
		val sb = new StringBuilder
		for (a ← MIN to MAX) {
			sb.append(if (a==MAX)
				'ё'
			else
				a.toChar)
		}
		sb.toString()
	}
}
