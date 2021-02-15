package com.leohetsch.weather.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.leohetsch.weather.model.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weather: Weather)

    @Query("SELECT * FROM weather WHERE cityId = :cityId")
    fun load(cityId: String): Flow<Weather>
}