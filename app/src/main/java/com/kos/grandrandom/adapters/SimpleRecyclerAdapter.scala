package com.kos.grandrandom.adapters

import android.content.Context
import android.view.{LayoutInflater, View, ViewGroup}
import androidx.recyclerview.widget.RecyclerView
import com.kos.grandrandom.holders.RandomItemHolder
import com.kos.grandrandom.randoms.NoneChance.DataItemChance

/**
  * Created by Kos on 19.10.2016.
  */
class SimpleRecyclerAdapter(context: Context,
							layoutRes: Int,
							viewToHolder: (View) ⇒ RandomItemHolder,
							val list: Array[DataItemChance]) extends RecyclerView.Adapter[RandomItemHolder]{

	private[this] val inflater=LayoutInflater.from(context)

	override def getItemCount: Int = {
		list.length
	}

	override def onBindViewHolder(holder: RandomItemHolder, position: Int): Unit = {
		holder.bind(position,list(position))
	}

//
//	override def getItemViewType(position: Int): Int = {
//		if (list(position)._1==R.string.randomPasswordName)
//			2
//		else
//			0
//	}

	override def onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomItemHolder = {
		viewToHolder(inflater.inflate(layoutRes,parent,false))
	}
}



