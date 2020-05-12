package itis.ru.paper.ui.restaurant_menu

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
import itis.ru.paper.model.Dish
import kotlinx.android.synthetic.main.item_dish.view.*

class DishesAdapter(private val clickListener: (Dish) -> Unit) :
    ListAdapter<Dish, DishesAdapter.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_dish,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Dish, clickListener: (Dish) -> Unit) {
            Glide.with(itemView)
                .load(item.photoUrl).listener(object : RequestListener<Drawable> {
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
                .into(itemView.iv_dish_photo)
            itemView.tv_dish_name.text = item.name
            itemView.tv_cost.text = item.cost
            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }


}