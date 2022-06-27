package com.example.androidcoursework.domain.repository

import com.example.androidcoursework.database.QuoteDao
import com.example.androidcoursework.domain.api.ApiProvider
import com.example.androidcoursework.domain.models.Quote

class QuoteRepository(private val apiProvider: ApiProvider, private val quoteDao: QuoteDao) :
    IQuoteRepository {
    override suspend fun getQuotes(): List<Quote> = apiProvider.getApi().getQuoteList()

    override suspend fun getQuotesFromDb(): List<Quote> = quoteDao.getQuotes()

    override suspend fun addQuote(quote: Quote) = quoteDao.insertQuote(quote)
}