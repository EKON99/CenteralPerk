package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class UserFeedResponseModel(
    val count: Int?,
    val next: String?,
    val results: List<Result>?
) : Parcelable