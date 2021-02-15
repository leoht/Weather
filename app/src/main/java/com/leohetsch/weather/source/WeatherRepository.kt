package com.leohetsch.weather.source

import com.leohetsch.weather.model.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val webService: WeatherWebService,
    private val weatherCache: WeatherCache
) {

    suspend fun getWeather(city: String): Weather {
        val cached = weatherCache.weather
        if (cached != null) {
            return cached
        }

        val fresh = webService.getWeather(city)
        weatherCache.weather = fresh

        return fresh
    }
}