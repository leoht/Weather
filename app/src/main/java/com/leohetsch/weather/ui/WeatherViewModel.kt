package com.leohetsch.weather.ui

import androidx.lifecycle.*
import com.leohetsch.weather.model.Weather
import com.leohetsch.weather.source.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
        private val weatherRepository: WeatherRepository
): ViewModel() {
    val city: String = savedStateHandle["city"] ?: "London"

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    init {
        viewModelScope.launch {
            _weather.value = weatherRepository.getWeather(city)
        }
    }
}