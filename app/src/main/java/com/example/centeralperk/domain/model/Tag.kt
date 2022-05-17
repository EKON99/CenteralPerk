package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Tag(
    val id: Int?,
    val post_id: Int?,
    val tags: String?
) : Parcelable