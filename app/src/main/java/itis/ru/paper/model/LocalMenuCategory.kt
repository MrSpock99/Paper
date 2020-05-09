package itis.ru.paper.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocalMenuCategory(val name: String, val dishes: List<Dish>) : Parcelable