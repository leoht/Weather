package com.leohetsch.weather

import com.leohetsch.weather.source.WeatherCache
import com.leohetsch.weather.source.WeatherWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object WeatherModule {
    @Provides
    fun providesApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val url = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter("apiKey", "eb1803516f5280e20debd07821a6e249")
            .build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Provides
    fun providesOkHttpClient(apiKeyInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://api.openweathermap.org/data/2.5/")
        .client(client)
        .build()

    @Provides
    fun providesWeatherService(retrofit: Retrofit): WeatherWebService =
        retrofit.create(WeatherWebService::class.java)

    @Provides
    fun providesWeatherCache() = WeatherCache()
}