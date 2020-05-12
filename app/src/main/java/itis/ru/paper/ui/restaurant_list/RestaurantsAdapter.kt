package itis.ru.paper.ui.restaurant_list

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import itis.ru.paper.R
import itis.ru.paper.model.LocalRestaurant
import kotlinx.android.synthetic.main.item_restaraunt.view.*
import kotlinx.android.synthetic.main.item_restaraunt.view.pb_downloading

class RestaurantsAdapter(private val clickListener: (LocalRestaurant) -> (Unit)) :
    ListAdapter<LocalRestaurant, RestaurantsAdapter.RestaurantHolder>(
        RestaurantDC()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RestaurantHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaraunt, parent, false), clickListener
    )

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<LocalRestaurant>) {
        submitList(data.toMutableList())
    }

    inner class RestaurantHolder(itemView: View,
        private val clickListener: (LocalRestaurant) -> (Unit)) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: LocalRestaurant) {
            itemView.setOnClickListener {
                clickListener(item)
            }
            itemView.tv_restaurant_name.text = item.name
            itemView.tv_description.text = item.description
            Glide.with(itemView)
                .load(item.photo)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        p2: com.bumptech.glide.request.target.Target<Drawable>?,
                        p3: Boolean
                    ): Boolean {
                        itemView.pb_downloading.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        p0: Drawable?,
                        p1: Any?,
                        p2: com.bumptech.glide.request.target.Target<Drawable>?,
                        p3: DataSource?,
                        p4: Boolean
                    ): Boolean {
                        itemView.pb_downloading.visibility = View.GONE
                        return false
                    }
                })
                .into(itemView.iv_restaurant_photo)
        }
    }

    private class RestaurantDC : DiffUtil.ItemCallback<LocalRestaurant>() {
        override fun areItemsTheSame(
            oldItem: LocalRestaurant,
            newItem: LocalRestaurant
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: LocalRestaurant,
            newItem: LocalRestaurant
        ): Boolean {
            return oldItem == newItem
        }
    }
}