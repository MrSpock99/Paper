package itis.ru.paper.model

import com.google.firebase.firestore.PropertyName

data class RemoteRestaurant(
    val name: String,
    @get:PropertyName("menu_category")
    @set:PropertyName("menu_category")
    var menu: List<RemoteMenuCategory>,
    val photoUrl: String,
    val description: String,
    @get:PropertyName("interior_photos")
    @set:PropertyName("interior_photos")
    var interiorPhotos: List<String>,
    @get:PropertyName("working_hours")
    @set:PropertyName("working_hours")
    var workingHours: String,
    @get:PropertyName("address")
    @set:PropertyName("address")
    var address: String
) {
    constructor() : this(
        name = "",
        menu = mutableListOf<RemoteMenuCategory>(),
        photoUrl = "",
        description = "",
        interiorPhotos = mutableListOf(),
        workingHours = "",
        address = ""
    )
}