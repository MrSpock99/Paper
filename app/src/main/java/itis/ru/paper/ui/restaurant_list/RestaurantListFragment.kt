package itis.ru.paper.ui.restaurant_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import itis.ru.paper.R
import itis.ru.paper.ui.base.BaseFragment
import itis.ru.paper.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import org.kodein.di.generic.instance

class RestaurantListFragment : BaseFragment() {
    private lateinit var adapter: RestaurantsAdapter

    private val viewModeFactory: ViewModelProvider.Factory by instance()
    private val viewModel: RestaurantListViewModel by lazy {
        ViewModelProviders.of(this, viewModeFactory).get(RestaurantListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeRestaurantList()
        viewModel.getRestaurantsList()
    }

    private fun observeRestaurantList() {
        viewModel.restaurantListLiveData.observe(this, Observer {
            if (it.data != null) {
                adapter.swapData(it.data)
            }
        })
    }

    private fun initRecycler() {
        adapter = RestaurantsAdapter {
            val action =
                RestaurantListFragmentDirections.actionRestaurantListFragmentToRestarauntMenuFragment(
                    restName = it.name,
                    menu = it.menu.toTypedArray(),
                    restPhoto = it.photo,
                    interiorPhotos = it.interiorPhotos.toTypedArray(),
                    address = it.address,
                    workingHours = it.workingHours
                )
            (activity as MainActivity).navController.navigate(action)
        }
        rv_restaurants.adapter = adapter
        rv_restaurants.layoutManager = LinearLayoutManager(activity)
    }
}