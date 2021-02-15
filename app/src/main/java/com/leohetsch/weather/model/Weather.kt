package com.leohetsch.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class WeatherMain(
    @SerializedName("temp")
    val temperature: Double,

    @SerializedName("feels_like")
    val temperatureFeelsLike: Double
)

//@Entity
data class Weather(
    @SerializedName("id")
    @PrimaryKey val cityId: Int,

    @SerializedName("name")
    val city: String,

    @SerializedName("main")
    val main: WeatherMain
) {
    fun getTemperatureCelcius() = main.temperature - 273.15
}