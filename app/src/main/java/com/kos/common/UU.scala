package com.kos.common

import android.view.View
import android.widget.TextView

/**
  * Created by Kos on 19.07.2016.
  */
object UU {
	def visible(trueView:View,falseView:View,isVisible:Boolean): Unit ={
		if (isVisible) {
			trueView.setVisibility(View.VISIBLE)
			falseView.setVisibility(View.INVISIBLE)
		}else{
			trueView.setVisibility(View.INVISIBLE)
			falseView.setVisibility(View.VISIBLE)
		}
	}

	def gone(view:View,isVisible:Boolean): Unit ={
		view.setVisibility(if (isVisible) View.VISIBLE else View.GONE)
	}

	def text(root:View ,resId:Int,text:String): Unit ={
		root.findViewById[TextView](resId).setText(text)
	}
	def text(view:View,text:String): Unit ={
		view.asInstanceOf[TextView].setText(text)
	}
	def text(view:View,text:Int): Unit ={
		view.asInstanceOf[TextView].setText(text)
	}
	def text(root:View ,resId:Int,text:Int): Unit ={
		root.findViewById[TextView](resId).setText(text)
	}
}
