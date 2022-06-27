package com.example.androidcoursework.domain.usecases.getQuote

import com.example.androidcoursework.domain.models.Quote
import com.example.androidcoursework.domain.repository.IQuoteRepository

class GetQuoteUseCase(private val iQuoteRepository: IQuoteRepository) : IGetQuoteUseCase{
    override suspend fun getQuotes(): List<Quote> = iQuoteRepository.getQuotes()
}