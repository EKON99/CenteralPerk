package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Data(
    val created_on: String?,
    val email: String?,
    val id: Int?,
    val state_id: Boolean?,
    val subscription: Boolean?,
    val token: String?,
    val user_type: Int?,
    val username: String?
) : Parcelable