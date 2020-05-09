package itis.ru.paper.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dish(val name: String, val photoUrl: String): Parcelable{
    constructor(): this(name = "", photoUrl = "")
}