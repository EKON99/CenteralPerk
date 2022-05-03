package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Data(
    val token: String,
    val username: String
) : Parcelable