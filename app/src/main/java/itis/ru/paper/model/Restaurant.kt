package itis.ru.paper.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val name: String,
    val menu: List<LocalMenuCategory>,
    val photo: String,
    val description: String,
    val interiorPhotos: List<String>,
    val workingHours: String,
    val address: String
) : Parcelable