package itis.ru.paper.interactor

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import itis.ru.paper.model.LocalMenuCategory
import itis.ru.paper.model.Restaurant
import itis.ru.paper.repository.RestaurantRepository

class RestaurantInteractor(private val repository: RestaurantRepository) {
    fun getRestaurantList(): Observable<MutableList<Restaurant>> {
        return repository.getRestaurantList()
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.fromIterable(it)
                    .map {
                        val localMenuCategoryList = mutableListOf<LocalMenuCategory>()
                        it.menu.forEach {
                            localMenuCategoryList.add(
                                LocalMenuCategory(
                                    name = it.name,
                                    dishes = it.dishes
                                )
                            )
                        }
                        Restaurant(
                            it.name,
                            localMenuCategoryList,
                            it.photoUrl,
                            it.description,
                            it.interiorPhotos,
                            it.workingHours,
                            it.address
                        )
                    }
                    .toList()
                    .toObservable()
            }
    }

}