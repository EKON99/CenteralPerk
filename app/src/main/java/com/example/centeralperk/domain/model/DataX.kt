package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataX(
    val bio: String?,
    val created_on: String?,
    val id: Int?,
    val profile_pic: String?,
    val skill: String?,
    val slug: String?,
    val updated_on: String?,
    val user_id: Int?,
    val username: String?
) : Parcelable