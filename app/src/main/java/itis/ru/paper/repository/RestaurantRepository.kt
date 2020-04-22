package itis.ru.paper.repository

import io.reactivex.Observable
import io.reactivex.Single
import itis.ru.paper.model.RemoteRestaurant
import itis.ru.paper.model.Restaurant

interface RestaurantRepository {
    fun getRestaurantList(): Observable<MutableList<RemoteRestaurant>>
}