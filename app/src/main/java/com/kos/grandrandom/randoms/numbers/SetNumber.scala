package com.kos.grandrandom.randoms.numbers


import scalaxy.loops._

/**
  * Created by Kos on 16.10.2016.
  */
class SetNumber(minValue:Int= 1, maxValue:Int=  99) extends MainNumber(minValue,maxValue){


	override def generate(count: Int): String = {

		val length=max-min+1

		if (length>=count) {

			val res=new Array[Int](count)
				if (length<10000){

					var i=0
					val values=new Array[Int](length)
					for (i ← 0 until length optimized)
						values(i)=min+i


					i=0
					while (i<count){
						val a:Int=random.nextInt(length-i)
						res(i)=values(a)
						values(a)=values(length-1-i)
						i+=1
					}


				}else{
					var i=0
					var c=0

					while (i< count && c<50){
						val a:Int=min+random.nextInt(length)

						var k=0
						var b=true

						while (k<i){
							if (res(k)==a){
								b=false
								k=i
							}
							else
								k+=1

						}

						if (b){
							res(i)=a
							i+=1
							c=0
						}else{
							c+=1
						}
					}

				}
				res.grouped(3).map(_.mkString(" ")).mkString("\n")


//			def ^(setNumbers:Seq[Int],cou:Int,res:Seq[Int]): Seq[Int] ={
//				if (cou>0) {
//					val a:Int=random.nextInt(setNumbers.length)
//					^(setNumbers.take(a) ++ setNumbers.drop(a+1),cou-1,res :+ setNumbers(a))
//				}else{
//					res
//				}
//			}
//			^(min to max, count,Seq.empty).mkString(" ")
		} else ""
	}


}
