package itis.ru.paper.interactor

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import itis.ru.paper.model.Restaurant
import itis.ru.paper.repository.RestaurantRepository

class RestaurantInteractor(private val repository: RestaurantRepository) {
    fun getRestaurantList(): Observable<MutableList<Restaurant>> {
        return repository.getRestaurantList()
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.fromIterable(it)
                    .map { Restaurant(it.name, it.menu, it.photoUrl) }
                    .toList()
                    .toObservable()
            }
    }

}