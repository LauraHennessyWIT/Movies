package org.wit.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var runtime: String = "",
                          var trailer: String = "",
                          var image: String = "") : Parcelable
