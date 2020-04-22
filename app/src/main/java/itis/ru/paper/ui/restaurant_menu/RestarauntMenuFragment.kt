package itis.ru.paper.ui.restaurant_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import itis.ru.paper.R
import itis.ru.paper.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_restaurant_menu.*

class RestarauntMenuFragment : BaseFragment() {
    val args: RestarauntMenuFragmentArgs by navArgs()
    private lateinit var adapter: DishesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurant_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //toolbarImage.setImageResource(args.restPhoto)
        collapsingToolbar.title = args.restName
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        adapter = DishesAdapter {

        }
        rv_dishes.adapter = adapter
        rv_dishes.layoutManager = LinearLayoutManager(context)
        adapter.submitList(args.menu.asList())
    }
}