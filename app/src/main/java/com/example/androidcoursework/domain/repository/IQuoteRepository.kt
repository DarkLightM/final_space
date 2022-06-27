package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.domain.models.Quote

interface IQuoteRepository {
    suspend fun getQuotes(): List<Quote>

    suspend fun getQuotesFromDb(): List<Quote>

    suspend fun addQuote(quote: Quote)
}