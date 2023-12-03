package uz.gita.luis.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.luis.weatherapp.data.common.ForecastRespons
import uz.gita.luis.weatherapp.databinding.ItemMainBinding
//ListAdapter<ForecastResponse,MainAdapter.ViewHola>(difutil)
class MainAdapter(private var data : ForecastRespons ): RecyclerView.Adapter<MainAdapter.ViewHola>() {

//    object difutil:DiffUtil.ItemCallback<ForecastResponse>(){
//        override fun areItemsTheSame(
//            oldItem: ForecastResponse,
//            newItem: ForecastResponse,
//        ): Boolean {
//            return oldItem.current == oldItem.current
//        }
//
//        override fun areContentsTheSame(
//            oldItem: ForecastResponse,
//            newItem: ForecastResponse,
//        ): Boolean {
//            return oldItem == newItem
//        }
//    }

    fun setForecast(forecastRespons : ForecastRespons){
        data = forecastRespons
    }

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
           binding.textSunsetOclock.text =  data.forecast.forecastday[0].astro.sunset.toString()
           binding.textSunriseOclock.text = data.forecast.forecastday[0].astro.sunrise.toString()
        }
    }

    override fun getItemCount(): Int = 1
}