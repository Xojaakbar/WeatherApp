package uz.gita.luis.weatherapp.data.common

data class ForecastRespons(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)