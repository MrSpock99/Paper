package itis.ru.paper.ui.restaurant_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import itis.ru.paper.R
import itis.ru.paper.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_restaurant_about.*

class AboutRestarauntFragment : BaseFragment() {
    val args: AboutRestarauntFragmentArgs by navArgs()
    private lateinit var interiorAdapter: InteriorAdapter
    private lateinit var menuCategoryAdapter: MenuCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInteriorRecycler()
        initMenuCategoryAdapter()
        setRestaurantInfo()
    }

    private fun setRestaurantInfo() {
        tv_restaurant_name.text = args.restName
        tv_work_hours_location.text = "${args.workingHours}, ${args.address}"
    }

    private fun initInteriorRecycler() {
        interiorAdapter = InteriorAdapter {

        }
        rv_restaurant_photos.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_restaurant_photos.adapter = interiorAdapter
        interiorAdapter.submitList(args.interiorPhotos.toList())
    }

    private fun initMenuCategoryAdapter() {
        menuCategoryAdapter = MenuCategoryAdapter {
            val action =
                AboutRestarauntFragmentDirections.actionRestarauntMenuFragmentToMenuCategoryFragment(
                    dishes = it.dishes.toTypedArray(),
                    name = it.name
                )
            rootActivity.navController.navigate(action)
        }
        rv_menu_category.layoutManager = LinearLayoutManager(context)
        rv_menu_category.adapter = menuCategoryAdapter
        menuCategoryAdapter.submitList(args.menu.toList())
    }
}