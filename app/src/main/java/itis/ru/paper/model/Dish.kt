package itis.ru.paper.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dish(val name: String): Parcelable{
    constructor(): this(name = "")
}