package itis.ru.paper.model

data class RemoteMenuCategory(val name: String, val dishes: List<Dish>) {
    constructor() : this(name = "", dishes = mutableListOf())
}