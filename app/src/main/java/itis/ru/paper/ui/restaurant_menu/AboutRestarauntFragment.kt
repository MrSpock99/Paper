package itis.ru.paper.ui.restaurant_menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
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
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        fab_add.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${args.phone}")
            startActivity(intent)
        }
    }

    private fun setRestaurantInfo() {
        tv_restaurant_name.text = args.restName
        tv_work_hours_location.text = "${args.workingHours}, ${args.address}"
    }

    private fun initInteriorRecycler() {
        interiorAdapter = InteriorAdapter(list = mutableListOf(), clickListener = {
            StfalconImageViewer.Builder<String>(
                context,
                interiorAdapter.list
            ) { view, image ->
                Glide.with(context!!).load(image).into(view)
            }.show()
        })
        rv_restaurant_photos.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_restaurant_photos.adapter = interiorAdapter
        interiorAdapter.submitList(args.interiorPhotos.toMutableList())
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