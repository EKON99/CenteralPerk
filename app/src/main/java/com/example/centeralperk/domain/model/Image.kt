package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Image(
    val id: Int?,
    val image: String?,
    val post_id: Int?
) : Parcelable