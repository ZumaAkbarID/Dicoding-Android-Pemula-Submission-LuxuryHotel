package com.rwa.luxuryhotel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hotel(
    val name: String,
    val location: String,
    val photo: String,
    val description: String,
) : Parcelable
