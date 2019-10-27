package com.kos.common.activities

import android.content.{DialogInterface, Intent}
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.annotation.{DimenRes, DrawableRes, IdRes, StringRes}
import androidx.appcompat.app.{AlertDialog, AppCompatActivity}
import androidx.appcompat.widget.Toolbar


/**
  * Created by Kos on 27.06.2016.
  */
object SActivity{
	val KEY_ID = "ID"
	val NONE_ID= -2
}
trait SActivity {

	self: AppCompatActivity =>


	@inline def find[T<:View](@IdRes id: Int) = findViewById[T](id)

	//@inline def snack(view: View, text: CharSequence) = Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()

	@inline def toast(text: CharSequence) = Toast.makeText(getApplicationContext,text, Toast.LENGTH_SHORT).show()
	@inline def toast(@StringRes textResId: Int) = Toast.makeText(getApplicationContext,textResId, Toast.LENGTH_SHORT).show()

	def drawable(@DrawableRes id: Int) =
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			getDrawable(id)
		}
		else {
			getResources.getDrawable(id)
		}


	def dimension(@DimenRes id: Int) =	getResources.getDimension(id)

	def addClick(@IdRes viewId:Int,clickListener: OnClickListener): Unit ={
		findViewById[View](viewId) match{
			case v:View ⇒ v.setOnClickListener(clickListener)
			case _ ⇒
		}
	}

	def fragmentManager = getSupportFragmentManager

	def show(activityClass: Class[_]) = startActivity(new Intent(this,activityClass))

	def showForResult(activityClass: Class[_],code:Int) = startActivityForResult(new Intent(this,activityClass),code)

	def show(activityClass: Class[_],id:Int) ={

		val intent=new Intent(this,activityClass)
		intent.putExtra(SActivity.KEY_ID,id)
		startActivity(intent)
	}

	def setupToolBar(@IdRes toolbarId:Int): Unit ={
		val toolbar = findViewById[Toolbar](toolbarId)
		setSupportActionBar(toolbar)
	}

	def setupToolBarWithBackButton(@IdRes toolbarId:Int): Unit ={
		setupToolBar(toolbarId)

		val actionBar=getSupportActionBar
		if (actionBar!=null){
			actionBar.setDisplayHomeAsUpEnabled(true)

		}
	}


	def addBackButton() {
		val actionBar = getSupportActionBar
		if (actionBar!=null){
			actionBar.setDisplayHomeAsUpEnabled(true)
			actionBar.setDisplayShowHomeEnabled(false)
		}
	}

	def getID: Int ={
		val intent=getIntent
		if (intent!=null) {
			intent.getIntExtra(SActivity.KEY_ID,SActivity.NONE_ID)
		}else{
			SActivity.NONE_ID
		}
	}

	def alertYesNo(resTitle: Int, resInfo: Int, yesOperator: () => Unit, noOperator: () => Unit ): Unit = {

		val alertDialog: AlertDialog = new AlertDialog.Builder(this).create
		alertDialog.setTitle(resTitle)
		alertDialog.setMessage(getString(resInfo))
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.yes),new DialogInterface
		.OnClickListener {
			override def onClick(dialog: DialogInterface, which: Int): Unit = {
				yesOperator()
			}
		} )
		alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.no),new DialogInterface
		.OnClickListener{
			override def onClick(dialog: DialogInterface, which: Int): Unit = {
				noOperator()
			}
		})

		alertDialog.show

		//		val dialog= new DialogFragment{
		//			override def onCreateDialog(savedInstanceState:Bundle):Dialog = {
		//				// Use the Builder class for convenient dialog construction
		//				val builder = new AlertDialog.Builder(getActivity())
		//				builder.setTitle(resTitle)
		//				builder.setMessage(getString(resInfo))
		//					.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		//						override def onClick(dialog:DialogInterface, id:Int) {
		//							yesOperator()
		//						}
		//					})
		//					.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		//						override def onClick(dialog :DialogInterface, id: Int) {
		//							noOperator()
		//						}
		//					})
		//				// Create the AlertDialog object and return it
		//				builder.create()
		//			}
		//		}
		//		dialog.

	}

}


