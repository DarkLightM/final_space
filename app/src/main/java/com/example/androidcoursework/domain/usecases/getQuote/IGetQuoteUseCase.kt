package com.example.androidcoursework.domain.usecases.getQuote

import com.example.androidcoursework.domain.models.Quote

interface IGetQuoteUseCase {
    suspend fun getQuotes(): List<Quote>
}