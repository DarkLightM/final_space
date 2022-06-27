package com.example.androidcoursework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidcoursework.domain.models.Location
import com.example.androidcoursework.domain.models.Quote

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getQuotes(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: Quote)

    @Query("SELECT * FROM quotes WHERE id= :id")
    fun getQuote(id: Int): Quote
}