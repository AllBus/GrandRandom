package com.kos.common

import android.widget.EditText

import scala.util.Try

/**
  * Created by Kos on 21.10.2016.
  */
object Utils {

	def tryInt(editText: EditText,defaultValue:Int):Int={
		Try(editText.getText.toString.toInt).getOrElse(defaultValue)
	}
}
