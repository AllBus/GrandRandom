package com.kos.grandrandom

import android.content.{ClipData, ClipboardManager, Context, Intent}
import android.os.Bundle
import android.text.{Editable, TextWatcher}
import android.util.TypedValue
import android.view.View.{OnClickListener, OnLongClickListener, OnTouchListener}
import android.view.{MenuItem, MotionEvent, View}
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget._
import com.kos.common.UU
import com.kos.grandrandom.randoms._
import com.kos.grandrandom.randoms.blocks._
import com.kos.grandrandom.randoms.numbers.{MainNumber, SetNumber}
import com.kos.grandrandom.randoms.words._

object RandomActivity {
	final val KEY_AMOUNT_SYMBOL = "key_amount_symbol_"
	final val KEY_DIAPAZON_BRGIN = "key_diapazon_begin_"
	final val KEY_DIAPAZON_END = "key_diapazon_end_"
}

class RandomActivity extends TActivity with OnLongClickListener with OnClickListener {

	import RandomActivity._
	import UU._
	import com.kos.common.Utils._

	lazy val resultLabel = find[TextView](R.id.resultLabel)
	lazy val id = getID.toInt
	lazy val diapazonLayout = find[View](R.id.diapazonLayout)
	lazy val labelAmount = find[TextView](R.id.labelAmount)


	lazy val symbolAmountEdit = find[EditText](R.id.symbolAmountEdit)
	lazy val diapazonBeginEdit = find[EditText](R.id.diapazonBeginEdit)
	lazy val diapazonEndEdit = find[EditText](R.id.diapazonEndEdit)

	lazy val passwordTypeLine = find[LinearLayout](R.id.passwordTypeLine)
	lazy val passwordUpperCheckBox = find[CheckBox](R.id.passwordUpperCheckBox)
	lazy val passwordLowerCheckBox = find[CheckBox](R.id.passwordLowerCheckBox)
	lazy val passwordDigitCheckBox = find[CheckBox](R.id.passwordDigitCheckBox)
	lazy val passwordSpecCheckBox = find[CheckBox](R.id.passwordSpecCheckBox)


	lazy val chance: IChance = {
		import R.string._
		id match {
			case `randomOrel` => new Orel()
			case `randomMainBlock` => new MainBlock()
			case `randomHorizontalBlock` => new HorizontalBlock()
			case `randomVerticalBlock` => new VerticalBlock()
			case `randomDigitBlock` => new DigitBlock()
			case `randomAnimalBlock` => new AnimalBlock()
			case `randomClockBlock` => new ClockBlock()
			case `randomCubeBlock` => new CubeBlock()
			case `randomFoodBlock` => new FoodBlock()
			case `randomPlantBlock` => new PlantBlock()
			case `randomSmileBlock` => new SmileBlock()
			case `randomTrigramBlock` => new TrigramBlock()
			case `randomPasswordBlock` => new PasswordBlock()
			case `randomLatinBlock` => new LatinBlock()
			case `randomCyrillicBlock` => new CyrillicBlock()
			case `randomPasswordWord` => new PasswordWord()
			case `randomMainNumber` => new MainNumber()
			case `randomSetNumber` => new SetNumber()
			case `randomHatchBlock` => new HatchBlock()
			case `randomHandsBlock` => new HandsBlock()
			case `randomTransportBlock` => new TransportBlock()
			case `randomZodiacBlock` => new ZodiacBlock()
			case `randomChessBlock` => new ChessBlock()
			case `randomGreekBlock` => new GreekBlock()
			case `randomHebrewBlock` => new HebrewBlock()
			case `randomArrowBlock` ⇒ new ArrowBlock()
			case `randomMoonBlock` ⇒ new MoonBlock()

			case `randomPasswordWordFull` ⇒
				gone(passwordTypeLine, true)
				setupPasswordLine(true, true, true, true)
				new PasswordWord(true, true, true, true)

			case `randomPasswordWordUpperDigitSpec` =>
				gone(passwordTypeLine, true)
				setupPasswordLine(true, false, true, true)
				new PasswordWord(true, false, true, true)
			case `randomPasswordWordUpperDigit` ⇒
				gone(passwordTypeLine, true)
				setupPasswordLine(true, false, true, false)
				new PasswordWord(true, false, true, false)
			case `randomPasswordWordLatin` =>
				gone(passwordTypeLine, true)
				setupPasswordLine(true, true, false, false)
				new PasswordWord(true, true, false, false)
			case _ ⇒ new Orel()
		}


	}

	val btn = Array(R.id.copyBtn, R.id.shareBtn, R.id.generateBtn)
	var l: Long = 0L

	lazy val valueTextWatcher = new TextWatcher {
		override def beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int): Unit = {}

		override def onTextChanged(s: CharSequence, start: Int, before: Int, count: Int): Unit = generate()

		override def afterTextChanged(s: Editable): Unit = {}
	}

	override protected def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_random)

		val sPref = getPreferences(Context.MODE_PRIVATE)
		try {
			val cs = chance.toString
			val (defCount) = chance match {

				case _: PasswordWord ⇒ "12"
				case _: LatinBlock | _: CyrillicBlock ⇒ "1"
				case _: MainNumber ⇒
					diapazonBeginEdit.setText(sPref.getString(KEY_DIAPAZON_BRGIN + cs, "1"))
					diapazonEndEdit.setText(sPref.getString(KEY_DIAPAZON_END + cs, "99"))

					diapazonBeginEdit.addTextChangedListener(valueTextWatcher)
					diapazonEndEdit.addTextChangedListener(valueTextWatcher)
					"3"
				case _ ⇒ "1"
			}


			symbolAmountEdit.setText(sPref.getString(KEY_AMOUNT_SYMBOL + cs, defCount))
			symbolAmountEdit.addTextChangedListener(valueTextWatcher)


		} catch {
			case _: Throwable ⇒
		}


		chance match {
			case _: DigitBlock ⇒ labelAmount.setText(R.string.labelDigitsAmount)
			case _: MainNumber ⇒ labelAmount.setText(R.string.labelNumberAmount)
			case _: CubeBlock ⇒ labelAmount.setText(R.string.labelCubsAmount)
			case _: Orel | _: TrigramBlock ⇒ labelAmount.setText(R.string.labelShotsAmount)
			case _: ClockBlock | _:ZodiacBlock | _:ChessBlock ⇒ labelAmount.setText(R.string.labelSmileAmount)
			case _ ⇒ labelAmount.setText(R.string.labelSymbolAmount)
		}

		find[View](R.id.generateBtn).setOnTouchListener(new OnTouchListener() {
			override def onTouch(v: View, event: MotionEvent): Boolean = {
				if (event.getAction == MotionEvent.ACTION_DOWN) {
					l = System.currentTimeMillis()
					chance.offset(l)
					///Log.d("Kos","time "+l)
					return false
				}

				return false

			}
		})

		gone(diapazonLayout, chance.hasDiapazon)
		//	resultLabel.setMovementMethod(new ScrollingMovementMethod())
		generate()

		try {
			setTitle(getString(id))
		} catch {
			case _: Throwable ⇒
		}
		addBackButton()


		//	val adapter = new ArrayAdapter[String](this,
		//		android.R.layout.simple_dropdown_item_1line, Array("1","2","3","4","6","8","10","16","20"))
		//	symbolAmountEdit.setAdapter(adapter)
		//	symbolAmountEdit.setThreshold(1);//will start working from first character


		btn.foreach { x ⇒
			val v = find[View](x)
			v.setOnClickListener(this)
			///	v.setOnLongClickListener(this)
		}
	}

	override def onPause(): Unit = {
		super.onPause()
		val sPref = getPreferences(Context.MODE_PRIVATE)
		val edit = sPref.edit()
		val cs = chance.toString

		edit.putString(KEY_AMOUNT_SYMBOL + cs, symbolAmountEdit.getText.toString)
		if (chance.hasDiapazon) {
			edit.putString(KEY_DIAPAZON_BRGIN + cs, diapazonBeginEdit.getText.toString)
			edit.putString(KEY_DIAPAZON_END + cs, diapazonEndEdit.getText.toString)
		}

		edit.commit()

	}


	override def onResume(): Unit = {
		super.onResume()
	}


	override def onOptionsItemSelected(item: MenuItem): Boolean = {
		item.getItemId match {
			case android.R.id.home ⇒ this.onBackPressed()
			case _ ⇒ super.onOptionsItemSelected(item)
		}
		true
	}

	override def onLongClick(v: View): Boolean = {

		//		if (hasText()) {
		//			// Don't show the cheat sheet for items that already show text.
		//			return false;
		//		}
		//
		//		final int[] screenPos = new int[2];
		//		final Rect displayFrame = new Rect();
		//		getLocationOnScreen(screenPos);
		//		getWindowVisibleDisplayFrame(displayFrame);
		//
		//		final Context context = getContext();
		//		final int width = getWidth();
		//		final int height = getHeight();
		//		final int midy = screenPos[1] + height / 2;
		//		int referenceX = screenPos[0] + width / 2;
		//		if (ViewCompat.getLayoutDirection(v) == ViewCompat.LAYOUT_DIRECTION_LTR) {
		//			final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		//			referenceX = screenWidth - referenceX; // mirror
		//		}
		//		Toast cheatSheet = Toast.makeText(context, mItemData.getTitle(), Toast.LENGTH_SHORT);
		//		if (midy < displayFrame.height()) {
		//			// Show along the top; follow action buttons
		//			cheatSheet.setGravity(Gravity.TOP | GravityCompat.END, referenceX, height);
		//		} else {
		//			// Show along the bottom center
		//			cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
		//		}
		//		cheatSheet.show();
		return true
	}

	override def onClick(v: View): Unit = {
		v.getId match {
			case R.id.generateBtn ⇒
				generate()

			case R.id.copyBtn ⇒
				val cm: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE).asInstanceOf[ClipboardManager]
				val clip: ClipData = ClipData.newPlainText("text", getLastRandomValue)
				cm.setPrimaryClip(clip)
				toast(R.string.toastCopyText)

			case R.id.shareBtn ⇒
				val share: Intent = new Intent(Intent.ACTION_SEND)
				share.setType("text/plain").putExtra(Intent.EXTRA_TEXT, getLastRandomValue)
				val chosenIntent = Intent.createChooser(share,getString(R.string.chooseSend))
				startActivity(chosenIntent)

			case _ ⇒
		}
	}

	def setupPasswordLine(hasUpper: Boolean, hasLower: Boolean, hasDigit: Boolean, hasSpec: Boolean) = {
		passwordUpperCheckBox.setChecked(hasUpper)
		passwordLowerCheckBox.setChecked(hasLower)
		passwordDigitCheckBox.setChecked(hasDigit)
		passwordSpecCheckBox.setChecked(hasSpec)

		val checkedChanged = new OnCheckedChangeListener {
			override def onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean): Unit =	generate()
		}

		passwordUpperCheckBox.setOnCheckedChangeListener(checkedChanged)
		passwordLowerCheckBox.setOnCheckedChangeListener(checkedChanged)
		passwordDigitCheckBox.setOnCheckedChangeListener(checkedChanged)
		passwordSpecCheckBox.setOnCheckedChangeListener(checkedChanged)
	}

	def getLastRandomValue = resultLabel.getText.toString

	def generate(): Unit = {
		val amount = tryInt(symbolAmountEdit, 1)
		var resize=true
		chance match {
			case number: MainNumber ⇒
				number.min = tryInt(diapazonBeginEdit, 1)
				number.max = tryInt(diapazonEndEdit, 99)
				resize=false

			case pass: PasswordWord ⇒
				pass.hasUpper = passwordUpperCheckBox.isChecked
				pass.hasLower = passwordLowerCheckBox.isChecked
				pass.hasDigit = passwordDigitCheckBox.isChecked
				pass.hasSpec = passwordSpecCheckBox.isChecked
				pass.resetSet()

			case _ ⇒
		}

		chance.offset(System.currentTimeMillis())

		val s=chance.generate(amount)
		resultLabel.setText(s)
		resultLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP,if (resize) {
			if (amount <= 2) 		96
			else if (amount <=6) 	72
			else if (amount <=12) 	48
			else	36

		}else{
			36
		})

	//	println(chance.allValues)
//		resultLabel.setText(chance.allValues)
	}


}