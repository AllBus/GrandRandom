package com.kos.grandrandom.randoms.blocks

import scalaxy.loops._

/**
  * Created by Kos on 16.10.2016.
  */
class HatchBlock extends PlantBlock{
	override val MIN = 0x25a0
	override val MAX = 0x25a9

	override val DIV = 0x25a2
//
//
//	override def generate(count: Int): String = {
//		val sb = new StringBuilder()
//		val length=MAX-MIN+1
//		for (i ← 1 to count optimized) {
//			sb.append {
//				val a=MIN+random.nextInt(length)
//				(if (a>=DIV) a+1	else a).toChar
//			}
//			if (i%3==0 && i!=count){
//				sb.append(' ')
//			}
//		}
//		sb.toString()
//	}
//
//	override def allValues= {
//		val sb = new StringBuilder
//		for (a ← MIN to MAX) {
//			sb.append((if (a>=DIV) a+1 else a).toChar)
//		}
//		sb.toString()
//	}

}
