package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Result(
    val content: String?,
    val created_by: Int?,
    val created_on: String?,
    val id: Int?
) : Parcelable