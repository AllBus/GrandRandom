package com.kos.grandrandom.randoms.words



import com.kos.grandrandom.randoms.IChance

import scala.util.Random
import scalaxy.loops._

/**
  * Генератор случайного пароля
  * Created by Kos on 16.10.2016.
  */
class PasswordWord(
					  var hasUpper: Boolean = true,
					  var hasLower: Boolean = true,
					  var hasDigit: Boolean = true,
					  var hasSpec: Boolean = true
				  ) extends IChance {

	val random = new Random(System.currentTimeMillis())


	val DIGIT = '0' to '9' mkString ""
	val LOWER = 'a' to 'z' mkString ""
	val UPPER = 'A' to 'Z' mkString ""
	val SPEC = "!@#$%^&*()'\":;{}[]_-+=/\\|?.,<>~`"

	private[this] var chars = ""
	resetSet()


	def resetSet(): Unit = {
		chars =
			(if (hasDigit) DIGIT else "") +
				(if (hasLower) LOWER else "") +
				(if (hasUpper) UPPER else "") +
				(if (hasSpec) SPEC else "")
	}

	def generate(): String = {
		generate(1)
	}

	def generate(count: Int): String = {
		val sb = new StringBuilder()
		val length = chars.length

		if (length > 0) {

			val sdvig =
				(if (hasDigit) 1 else 0) +
					(if (hasLower) 1 else 0) +
					(if (hasUpper) 1 else 0) +
					(if (hasSpec) 1 else 0)

			if (count - sdvig > 0) {
				for (i ← 0 until count - sdvig optimized) {
					sb.append {
						chars(random.nextInt(length))
					}
				}

				if (hasDigit) sb.insert(random.nextInt(sb.length), DIGIT(random.nextInt(DIGIT.length)))
				if (hasLower) sb.insert(random.nextInt(sb.length), LOWER(random.nextInt(LOWER.length)))
				if (hasUpper) sb.insert(random.nextInt(sb.length), UPPER(random.nextInt(UPPER.length)))
				if (hasSpec) sb.insert(random.nextInt(sb.length), SPEC(random.nextInt(SPEC.length)))
			}else{
				for (i ← 0 until count optimized) {
					sb.append {
						chars(random.nextInt(length))
					}
				}
			}

		}

		sb.toString()
	}

	override def allValues = chars
	override def offset(offset: Long): Unit = {
		val k=(offset%1000).toInt
		for (i ← 1 to k optimized)
			random.nextLong()
	}
}
