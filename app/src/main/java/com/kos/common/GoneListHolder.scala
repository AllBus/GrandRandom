package com.kos.common

import android.view.{LayoutInflater, View, ViewGroup}

/**
  * Created by Kos on 10.07.2016.
  */
class GoneListHolder[T](
					   val layout:ViewGroup,
					   private[this] val resLayoutId: Int,
					   private[this] val layoutInflater: LayoutInflater,
					   getItemMethod:  View ⇒ T  = {	x : View ⇒ x.asInstanceOf[T]	}
				   ) {

	def check(checker: (T, Int) ⇒ Boolean):Boolean = {
		if (layout==null)
			return true
		for (i ← 0 until layout.getChildCount) {
			if (!checker(getItemMethod(layout.getChildAt(i)),i))
				return false
		}
		true
	}

	def regenerate(count: Int, binder: (T, Int) ⇒ Unit):Unit = {
		if (layout==null)
			return

		while (count>layout.getChildCount){
			val view = layoutInflater.inflate(resLayoutId,layout,false)
			layout.addView(view)
		}


		for (i ← 0 until count) {
			val v=layout.getChildAt(i)
			v.setVisibility(View.VISIBLE)
			binder(getItemMethod(v),i)
		}

		for (i ← count until layout.getChildCount) {
			val v=layout.getChildAt(i)
			v.setVisibility(View.GONE)
		}
	}

	def bind(position: Int, binder: (T, Int) ⇒ Unit): Unit ={
		if (layout==null)
			return

		if (position>= 0 && position<layout.getChildCount)
			binder(getItemMethod(layout.getChildAt(position)),position)

	}
}