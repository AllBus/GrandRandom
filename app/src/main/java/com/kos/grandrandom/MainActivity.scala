package com.kos.grandrandom


import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.{LinearLayoutManager, RecyclerView}
import com.kos.grandrandom.R.string._
import com.kos.grandrandom.adapters.SimpleRecyclerAdapter
import com.kos.grandrandom.holders.RandomItemHolder
import com.kos.grandrandom.randoms.NoneChance.DataItemChance


class MainActivity extends TActivity {

	lazy val list = find[RecyclerView](R.id.list)

	lazy val randomList = {
		import R.string._

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
			Array[DataItemChance](

			randomOrelName → Array(
					randomOrel -> "①ⓞ①",
					randomCubeBlock -> "⚁⚁⚄",
					randomTrigramBlock -> "☲☵☱"
				),
			randomNumberName →Array(
				randomMainNumber -> "5 3 5",
				randomSetNumber -> "7 8 4",
				randomDigitBlock -> "150"),


			randomSmileName → Array(
				randomSmileBlock -> "😈😪😍",
				randomHandsBlock → "\uD83D\uDC48\uD83D\uDC4B\uD83D\uDC4C",
				randomAnimalBlock -> "🐳🐤🐜",
				randomArrowBlock → "⇒⇓⇖",
				randomFoodBlock -> "\uD83C\uDF57🍬🍑",
				randomPlantBlock -> "🌰🌺🌾",
				randomClockBlock -> "🕕🕞🕓",
				randomTransportBlock → "\uD83D\uDE82\uD83D\uDE90\uD83D\uDE93",
				randomZodiacBlock → "♑♐♎",
				randomChessBlock → "♜♘♚",
				randomMoonBlock → "\uD83C\uDF11\uD83C\uDF13\uD83C\uDF14"
			),

			randomPasswordName → Array(
				randomPasswordWordFull -> "Az9~",
				randomPasswordWordUpperDigitSpec -> "A9~",
				randomPasswordWordUpperDigit -> "A9",
				randomPasswordWordLatin -> "Az"
			),
			randomAlphabetName → Array(
				randomLatinBlock -> "zxs",
				randomCyrillicBlock -> "мцб",
				randomGreekBlock →"ανδ",
				randomHebrewBlock →"אבג"
				//	randomPasswordBlock -> "J<b"
			),
			randomBlockName → Array(
				randomHorizontalBlock -> "▆▂▄",
				randomVerticalBlock -> "▊▌▏",
				randomHatchBlock → "▨▩▤"
			    //	randomMainBlock -> "┕├┉"
			)
		)
		else
			Array[DataItemChance](

				randomOrelName → Array(
					randomOrel -> "①ⓞ①",
					randomCubeBlock -> "⚁⚁⚄"
				),
				randomNumberName →Array(
					randomMainNumber -> "5 3 5",
					randomSetNumber -> "7 8 4",
					randomDigitBlock -> "150"),
				randomSmileName → Array(
					randomChessBlock → "♜♘♚",
					randomArrowBlock → "⇒⇓⇖"
				),
				randomPasswordName → Array(
					randomPasswordWordFull -> "Az9~",
					randomPasswordWordUpperDigitSpec -> "A9~",
					randomPasswordWordUpperDigit -> "A9",
					randomPasswordWordLatin -> "Az"
				),
				randomAlphabetName → Array(
					randomLatinBlock -> "zxs",
					randomCyrillicBlock -> "мцб",
					randomGreekBlock →"ανδ",
					randomHebrewBlock →"אבג"
					//	randomPasswordBlock -> "J<b"
				),
				randomBlockName → Array(
					randomHorizontalBlock -> "▆▂▄",
					randomVerticalBlock -> "▊▌▏",
					randomHatchBlock → "▨▩▤"
					//	randomMainBlock -> "┕├┉"
				)
			)

	}

	lazy val itemClick = new OnClickListener {
		override def onClick(v: View): Unit = {
			v.getTag  match {
				case x:Integer ⇒ show(classOf[RandomActivity],x)
				case _ ⇒
			}
		}
	}

	lazy val adapter = new SimpleRecyclerAdapter(
		this,
		R.layout.item_main,
		new RandomItemHolder(_, itemClick),
		randomList
	)


	override protected def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
		list.setAdapter(adapter)


	}
}