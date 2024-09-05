package com.example.a24_09_sdv_m5a

import com.example.a24_09_sdv_m5a.model.UserBean


class MyLiveData(value: String) {

    var value: String = value
        set(newValue) {
            field = newValue
            action?.invoke(newValue)
        }

    var action: ((String) -> Unit)? = null
}

fun main() {

    exo3()

}

data class PersonBean(var name:String, var note:Int)

fun exo3(){

    val list = arrayListOf(PersonBean ("toto", 16),
        PersonBean ("Tata", 20),
        PersonBean ("Toto", 8),
        PersonBean ("Charles", 14))

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >=10 }.joinToString("\n") { "-${it.name} : ${it.note}"})

    println("\n\nAfficher combien il y a de Toto dans la classe ?")

    val isToto : (PersonBean)-> Boolean = {it.name.equals("Toto",true)}
    //V1
    var nbToto = list.count {
        it.name.equals("Toto",true)
    }
    //V2
    list.count(isToto)
    println("nbToto=$nbToto")

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    nbToto = list.count { isToto(it) && it.note>= 10 }
    println("nbToto=$nbToto")

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val moyenne = list.map { it.note }.average()
    nbToto = list.count { isToto(it) && it.note>= moyenne }

    println("\n\nAfficher la list triée par nom sans doublon")
    list.sortedBy { it.name }.distinctBy { it.name }

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.filter { it.note < 10 }.forEach { it.note++ }

    println("\n\nAjouter un point à tous les Toto")
    list.filter (isToto).forEach { it.note++ }

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    val min = list.minOf { it.note }
    list.removeIf { it.note == min }

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    list.filter { it.note >= 10 }.sortedBy { it.name }.joinToString { it.name }

    //TODO Créer une variable isToto contenant une lambda qui teste si un PersonBean s'appelle Toto

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")
}

fun exo2() {
    val compareUsersByName: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }
    val compareUsersByOld: (UserBean, UserBean) -> UserBean = { u1, u2 -> if (u1.old >= u2.old) u1 else u2 }

//    var user1 = UserBean("Toto", 25)
//    var user2 = UserBean("Tata", 18)
//
//    var maxUserName = compareUsersByName(user1, user2)
//    println("maxUserName=$maxUserName")
//
//    var maxUserOld = compareUsersByOld(user1, user2)
//    println("maxUserOld=$maxUserOld")

    val u1 = UserBean("Bob", 19)
    val u2 = UserBean("Toto", 45)
    val u3 = UserBean("Charles", 26)

    val maxUser = compareUsers(u1, u2, u3, compareUsersByOld)
    println(maxUser)

    val near30 = compareUsers(u1, u2, u3) { a, b ->
        if (Math.abs(30 - a.old) <= Math.abs(30 - b.old))
            a
        else b
    }
    println("near30=$near30")

}

fun compareUsers(u1: UserBean, u2: UserBean, u3: UserBean, comparator: (UserBean, UserBean) -> UserBean): UserBean {
    val maxU1U2 = comparator(u1, u2)
    val max = comparator(maxU1U2, u3)
    return max
}


fun exo1() {
    //Déclaration
    val lower: (String) -> Unit = { it: String -> println(it.uppercase()) }
    val lower2 = { it: String -> println(it.uppercase()) }
    val lower3: (String) -> Unit = { it -> println(it.uppercase()) }
    val lower4: (String) -> Unit = { println(it.uppercase()) }

    var minToHour: ((Int?) -> Pair<Int, Int>?)? = { if (it != null) Pair(it / 60, it % 60) else null }
    minToHour = null

    //Appel
    lower("Coucou")

    var hour: (Int) -> Int = { it / 60 }
    println(hour(123))

    var max: (Int, Int) -> Int = { a, b -> Math.max(a, b) }
    println(max(5, 6))

    var reverse: (String) -> String = { it.reversed() }
    println(reverse("Salut"))

    println(minToHour?.invoke(null))
}