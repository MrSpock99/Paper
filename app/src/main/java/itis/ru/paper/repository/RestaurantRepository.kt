package itis.ru.paper.repository

import io.reactivex.Observable
import itis.ru.paper.model.RemoteRestaurant

interface RestaurantRepository {
    fun getRestaurantList(): Observable<MutableList<RemoteRestaurant>>
}