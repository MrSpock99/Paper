package itis.ru.paper.ui.restaurant_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import itis.ru.paper.R
import itis.ru.paper.model.Restaurant
import kotlinx.android.synthetic.main.item_restaraunt.view.*

class RestaurantsAdapter(private val clickListener: (Restaurant) -> (Unit)) :
    ListAdapter<Restaurant, RestaurantsAdapter.RestaurantHolder>(
        RestaurantDC()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RestaurantHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaraunt, parent, false), clickListener
    )

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Restaurant>) {
        submitList(data.toMutableList())
    }

    inner class RestaurantHolder(itemView: View,
        private val clickListener: (Restaurant) -> (Unit)) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Restaurant) {
            itemView.setOnClickListener {
                clickListener(item)
            }
            itemView.tv_restaurant_name.text = item.name
            itemView.tv_description.text = item.description
            Glide.with(itemView)
                .load(item.photo)
                .into(itemView.iv_restaurant_photo)
        }
    }

    private class RestaurantDC : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(
            oldItem: Restaurant,
            newItem: Restaurant
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Restaurant,
            newItem: Restaurant
        ): Boolean {
            return oldItem == newItem
        }
    }
}