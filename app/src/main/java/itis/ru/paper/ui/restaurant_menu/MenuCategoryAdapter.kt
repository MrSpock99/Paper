package itis.ru.paper.ui.restaurant_menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import itis.ru.paper.R
import itis.ru.paper.model.LocalMenuCategory
import kotlinx.android.synthetic.main.item_menu_category.view.*

class MenuCategoryAdapter(private val clickListener: (LocalMenuCategory) -> Unit) :
    ListAdapter<LocalMenuCategory, MenuCategoryAdapter.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_menu_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: LocalMenuCategory, clickListener: (LocalMenuCategory) -> Unit) {
            //Glide.with(itemView).load(url).into(itemView.iv_restaurant_photo)
            itemView.tv_category_name.text = item.name
            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LocalMenuCategory>() {
        override fun areItemsTheSame(
            oldItem: LocalMenuCategory,
            newItem: LocalMenuCategory
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: LocalMenuCategory,
            newItem: LocalMenuCategory
        ): Boolean {
            return oldItem == newItem
        }
    }
}