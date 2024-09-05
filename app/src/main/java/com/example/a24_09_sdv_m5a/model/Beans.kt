package com.example.a24_09_sdv_m5a.model

fun main() {
    var car = CarBean("Seat", "Leon")
    car.color = "Grise"

    println("C'est une ${car.marque} ${car.model} ${car.color}")
    println(car)
}

data class UserBean(var name:String, var old : Int)

data class CarBean(var marque:String, var model:String) {
    var color : String = ""
}