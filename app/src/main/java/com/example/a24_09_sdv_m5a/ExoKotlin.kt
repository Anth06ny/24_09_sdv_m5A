package com.example.a24_09_sdv_m5a

fun main() {
    //paramètres nommés


}



fun boulangerie(nbCroi:Int = 0, nbBag : Int = 0, nbSand : Int = 0): Double
    = nbCroi * PRICE_CROISSANT + nbBag*  PRICE_BAGUETTE + nbSand * PRICE_SANDWICH



//Fonction avec p2 nullable et p1 et p3 non nullable
//p2 à une valeur par défaut de 1.2
fun name(p1:Int, p2:String?, p3 : Double = 1.2) : Int {

    //Déclaration variable
    var v1 : String? = p2

    //Safe check + elvis operator
    println(v1?.uppercase() ?: "-")

    //If else
    if(v1 != null) {
        println(v1.uppercase())
    }
    else {
        println("-")
    }

    return p1
}