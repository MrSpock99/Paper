package itis.ru.paper.model

data class RemoteRestaurant(val name: String, val menu: List<Dish>, val photoUrl: String){
    constructor(): this(name= "",menu = mutableListOf<Dish>(),photoUrl = "")
}