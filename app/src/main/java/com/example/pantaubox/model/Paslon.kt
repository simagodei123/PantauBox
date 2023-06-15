package com.example.pantaubox.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Paslon(
    val name1: String,
    val name2: String,
    val photo: String,
    val norut: String,
    val deskripsi: String,
) : Parcelable