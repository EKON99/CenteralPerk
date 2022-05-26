package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class UserProfileResponseModel(
    val `data`: DataX?,
    val message: String?,
    val status: Boolean?
) : Parcelable