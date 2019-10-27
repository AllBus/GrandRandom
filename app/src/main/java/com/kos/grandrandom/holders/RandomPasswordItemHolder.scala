package com.kos.grandrandom.holders

import android.view.View.OnClickListener
import android.view.{LayoutInflater, View}
import android.widget.{LinearLayout, TextView}
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.kos.common.{GoneListHolder, UU}
import com.kos.grandrandom.R
import com.kos.grandrandom.randoms.NoneChance
import com.kos.grandrandom.randoms.NoneChance.DataItemChance


/**
  * Created by Kos on 19.10.2016.
  */
class RandomPasswordItemHolder(topView: View, clickListener: OnClickListener) extends RecyclerView.ViewHolder(topView) {

	@inline def find[T<:View](@IdRes id: Int) = itemView.findViewById[T](id)

	val name = find[TextView](R.id.title)
	val blockList = find[LinearLayout](R.id.blockList)
	itemView.setOnClickListener(clickListener)
	val holder = new GoneListHolder[View](blockList, R.layout.item_main_card, LayoutInflater.from(itemView.getContext),
		identity)


	var data: DataItemChance = NoneChance.NoneData

	val binder = (v: View, pos: Int) ⇒ {
		v.setTag(data._2(pos))
		v.setOnClickListener(clickListener)
		UU.text(v, R.id.name, data._2(pos)._1)
		UU.text(v, R.id.simpleName, data._2(pos)._2)
	}

	def bind(position: Int, element: DataItemChance) {
		data = element

		holder.regenerate(element._2.length, binder)

		UU.text(name, element._1)

	}
}
