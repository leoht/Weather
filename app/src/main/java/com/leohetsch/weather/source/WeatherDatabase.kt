package com.leohetsch.weather.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leohetsch.weather.model.Weather

//@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}