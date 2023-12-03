package uz.gita.luis.weatherapp.app

import android.app.Application
import uz.gita.luis.weatherapp.data.source.remote.apies.Network
import uz.gita.luis.weatherapp.domain.repositories.weatherrepositoryimpl.WeatherRepositoryClass

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        val client = Network.createClient(this)
        val retrofit = Network.createRetrofit(client)
        val currentApi = Network.getCurrentApi(retrofit)
        WeatherRepositoryClass.init(currentApi)
    }
}