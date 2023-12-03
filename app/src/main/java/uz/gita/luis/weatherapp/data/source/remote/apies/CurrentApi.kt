package uz.gita.luis.weatherapp.data.source.remote.apies

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.luis.weatherapp.data.common.ForecastRespons

interface CurrentApi {

    @GET("forecast.json")
    suspend fun getWeatherByCityByDays( @Query("Key") key:String, @Query("q") nameOfCity:String, @Query("days") days:Int): Response<ForecastRespons>

}