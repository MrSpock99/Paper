package itis.ru.paper.ui.restaurant_list

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import itis.ru.paper.interactor.RestaurantInteractor
import itis.ru.paper.model.LocalRestaurant
import itis.ru.paper.ui.base.BaseViewModel
import itis.ru.paper.utils.Response

class RestaurantListViewModel(private val interactor: RestaurantInteractor) : BaseViewModel() {
    val restaurantListLiveData = MutableLiveData<Response<MutableList<LocalRestaurant>>>()

    fun getRestaurantsList() {
        disposables.add(
            interactor.getRestaurantList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    restaurantListLiveData.value = Response.success(it)
                }, {
                    restaurantListLiveData.value = Response.error(it)
                })
        )
    }
}