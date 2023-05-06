package com.drcmind.androidasynchroniouswithflow

data class UiState(
    val isLoading : Boolean = false,
    val quotesList: List<Quote> = emptyList()
)
