package com.kos.grandrandom.randoms.blocks
import scalaxy.loops._
/**
  * Created by Kos on 16.10.2016.
  */
class TransportBlock extends ClockBlock{
	override val MIN = 0x1f680
	override val MAX = 0x1f6b2
	val values=Array[Int](
		0x1f680,
		0x1f681,
		0x1f682,
		0x1f683,
		0x1f684,
		0x1f686,
		0x1f687,
		0x1f68a,
		0x1f68b,
		0x1f68c,
		0x1f68e,
		0x1f690,
		0x1f691,
		0x1f692,
		0x1f693,
		0x1f695,
		0x1f697,
		0x1f699,
		0x1f69e,
		0x1f69f,
		0x1f6a0,
		0x1f6a1,
		0x1f6a2,
		0x1f6a3,
		0x1f6a4,
		0x1f6b2,
		0x26f4,
		0x26f5,
		0x2708

	)

	override def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length=values.length
		for (i ← 1 to count optimized) {
			sb.appendAll {
				Character.toChars(values(random.nextInt(length)))
			}
			if (i%3==0 && i!=count){
				sb.append(' ')
			}
		}
		sb.toString()
	}

	override def allValues= {
		val sb = new StringBuilder
		for (i ← values) {
			sb.appendAll(Character.toChars(i))

		}
		sb.toString()
	}
}
