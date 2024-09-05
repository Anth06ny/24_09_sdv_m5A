package com.example.a24_09_sdv_m5a.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    val viewModel = MainViewModel()
    viewModel.loadWeathers("")

    while (viewModel.runInProgress) {
        println("Tache en cours : ${viewModel.runInProgress} list=${viewModel.dataList}")
        Thread.sleep(500)
    }
    println("Tache en cours : ${viewModel.runInProgress}")
    println("List : ${viewModel.dataList}")
    println("ErrorMessage : ${viewModel.errorMessage}")
}

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""

data class PictureBean(val id: Int, val url: String, val title: String, val longText: String)

class MainViewModel : ViewModel() {

    //Données utilisées
    var dataList: List<PictureBean> = ArrayList()

    //Tache en cours
    var runInProgress = false

    //Gestion de l'erreur
    var errorMessage = ""

    init {//Création d'un jeu de donnée au démarrage
        loadFakeData()
    }

    fun loadFakeData(){
        dataList = listOf(PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }

    fun loadWeathers(cityName: String) {

        runInProgress = true
        errorMessage = ""


        //Tache asynchrone
        viewModelScope.launch(Dispatchers.Default) {
            try {
                dataList = WeatherAPI.loadWeathers(cityName).map {
                    PictureBean(
                        it.id,
                        it.weather.getOrNull(0)?.icon ?: "",
                        it.name,
                        "Il fait ${it.main.temp}° à ${it.name}(id=${it.id}) avec un vent de ${it.wind.speed} m/s\n"
                    )
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage = e.message ?: "Une erreur est survenue"
            }
            runInProgress = false
        }
    }
}