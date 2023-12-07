package uz.gita.luis.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.luis.weatherapp.data.common.ForecastRespons
import uz.gita.luis.weatherapp.databinding.ItemMainBinding

class MainAdapter(private var data : ForecastRespons ): RecyclerView.Adapter<MainAdapter.ViewHola>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
        return ViewHola(ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind()
    }

    inner class ViewHola(private val binding: ItemMainBinding): ViewHolder(binding.root){
       fun bind(){
            binding.recyclerVertical.adapter = VerticalAdapter(data.forecast.forecastday)
            binding.recyclerHorizontal.adapter = HorizontalAdapter(data.forecast.forecastday)
           binding.textSunsetOclock.text =  data.forecast.forecastday[0].astro.sunset
           binding.textSunriseOclock.text = data.forecast.forecastday[0].astro.sunrise
        }
    }

    override fun getItemCount(): Int = 1
}