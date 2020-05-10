package itis.ru.paper.ui.restaurant_menu

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itis.ru.paper.R
import itis.ru.paper.ui.base.BaseFragment
import itis.ru.paper.utils.dpToPx
import kotlinx.android.synthetic.main.fragment_menu_category.*

class MenuCategoryFragment : BaseFragment() {
    val args: MenuCategoryFragmentArgs by navArgs()
    private lateinit var adapter: DishesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        tv_category_name.text = args.name
    }

    private fun initRecycler() {
        adapter = DishesAdapter {

        }
        rv_dishes.layoutManager = GridLayoutManager(context, 2)
        rv_dishes.adapter = adapter
        val spacing = dpToPx(3)
        rv_dishes.setPadding(spacing, spacing, spacing, spacing)
        rv_dishes.clipToPadding = false
        rv_dishes.clipChildren = false
        rv_dishes.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })
        adapter.submitList(args.dishes.toList())
    }
}