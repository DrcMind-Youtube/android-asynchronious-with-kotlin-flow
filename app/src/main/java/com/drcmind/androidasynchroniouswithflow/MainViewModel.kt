package com.drcmind.androidasynchroniouswithflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

    private val remoteList = listOf(
        Quote("Louis", "L'amour n'a pas de prix"),
        Quote("Amani", "La puissance obéit la puissance"),
        Quote("Tacite", "La communauté d'abord"),
        Quote("Alain", "Mesdames, mesdemoiselles et messieurs"),
        Quote("Yannick", "Seuls les gondwanais connaissent Gondwana")
    )

    val getQuotes = flow {
            emit(Result.Loading)
            val list = remoteList
            delay(3000)
            emit(Result.Success(list))
        }


}



