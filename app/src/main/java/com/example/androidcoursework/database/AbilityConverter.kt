package com.example.androidcoursework.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors

class AbilityConverter {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromAbilities(list: MutableList<String>): String {
        val stringBuilder = StringBuilder()
        for(item in list){
            stringBuilder.append(item).append("/")
        }
        return stringBuilder.toString()
    }

    @TypeConverter
    fun toAbilities(data: String): MutableList<String> {
        return data.split("/".toRegex()).toMutableList()
    }
}