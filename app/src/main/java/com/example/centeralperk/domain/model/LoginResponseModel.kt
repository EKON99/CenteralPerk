package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class LoginResponseModel(
    val `data`: Data?,
    val message: String?,
    val status: Boolean?
) : Parcelable