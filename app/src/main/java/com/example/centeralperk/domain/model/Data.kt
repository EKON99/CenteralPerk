package com.example.centeralperk.domain.model

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@kotlinx.parcelize.Parcelize
data class Data(
    val token: String?,
    val username: String?
) : Parcelable