package com.leohetsch.weather

import javax.inject.Inject

class WeatherRepository @Inject constructor(
        private val webService: WeatherWebService
) {

    suspend fun getWeather(city: String) = webService.getWeather(city)
}