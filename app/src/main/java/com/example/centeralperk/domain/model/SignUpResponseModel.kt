package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class SignUpResponseModel(
    val email: String,
    val name: String,
    val password: String,
    val username: String
) : Parcelable