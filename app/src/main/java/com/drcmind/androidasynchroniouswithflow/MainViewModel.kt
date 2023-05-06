package com.drcmind.androidasynchroniouswithflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(UiState())
    val stateFlow = _stateFlow.asStateFlow()


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

    init {
        getQuotes()
    }

    fun getQuotes(){
        viewModelScope.launch {
            getQuotes.collect{result->
                when (result){
                    is Result.Loading->{
                        _stateFlow.value = _stateFlow.value.copy(
                            isLoading = true
                        )
                    }
                    is Result.Success->{
                        _stateFlow.value = _stateFlow.value.copy(
                            isLoading = false,
                            quotesList = result.data
                        )
                    }
                }
            }
        }

    }


}



