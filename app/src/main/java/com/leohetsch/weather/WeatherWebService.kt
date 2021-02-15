package com.leohetsch.weather

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface WeatherWebService {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String): Weather
}