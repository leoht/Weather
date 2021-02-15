package com.leohetsch.weather.source

import com.leohetsch.weather.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherWebService {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String): Weather
}