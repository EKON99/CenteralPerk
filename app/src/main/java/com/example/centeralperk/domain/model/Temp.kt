package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Temp(
    val `data`: Data?,
    val message: String?,
    val status: Int?,
    val url: String?
) : Parcelable