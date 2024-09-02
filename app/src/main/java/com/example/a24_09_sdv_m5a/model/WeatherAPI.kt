package com.example.a24_09_sdv_m5a.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


const val URL_API_WEATHER =
    "https://api.openweathermap.org/data/2.5/find?q=%s&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

//Utilisation
fun main() {
//    //Lance la requête et met le corps de la réponse dans html
//    var html : String = WeatherAPI.sendGet("https://www.google.fr")
//    //Affiche l'HTML
//    println("html=$html")

    val list: List<WeatherBean> = WeatherAPI.loadWeathers("Nice")
    for (city in list) {
        println("Il fait ${city.main.temp}° à ${city.name} (id=${city.id}) avec un vent de ${city.wind.speed} m/s)")
        println("-Description : ${city.weather.getOrNull(0)?.description ?: "-"}")
        println("-Icône : ${city.weather.getOrNull(0)?.icon ?: "-"}")
    }

    println(list)
//    var user = WeatherAPI.loadRandomUser()
//
//    println("Il s'appelle ${user.name} pour le contacter :")
//    println("Phone : ${user.coord?.phone ?: "-"}")
//    println("Mail : ${user.coord?.mail ?: "-"}")
}


object WeatherAPI {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadWeathers(city: String): List<WeatherBean> {
        val json = sendGet(URL_API_WEATHER.format(city))
        val data =  gson.fromJson(json, WeatherAroundBean::class.java)

        data.list.forEach{
            it.weather.forEach {
                it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png"
            }
        }

        return data.list
    }

    fun loadWeatherAround(lat:Double, lon:Double): List<WeatherBean> {
        //Réaliser la requête.
        val json: String = sendGet("https://api.openweathermap.org/data/2.5/find?lat=$lat&lon=$lon&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        return gson.fromJson(json, WeatherAroundBean::class.java).list
    }

    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}
/* -------------------------------- */
// API Weather
/* -------------------------------- */

data class WeatherAroundBean(var list : List<WeatherBean>)

data class WeatherBean(var id:Int, var main : TempBean,var wind : WindBean,var name : String, var weather : List<DescriptionBean>)
data class TempBean(var temp: Double)
data class WindBean(var speed: Double)

data class DescriptionBean(
    var description: String,
    var icon: String,
    var main: String
)
