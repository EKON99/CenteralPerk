package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ResultX(
    val Tags: List<Tag>?,
    val content: String?,
    val id: Int?,
    val image: String?,
    val images: List<Image>?,
    val username: String?
) : Parcelable