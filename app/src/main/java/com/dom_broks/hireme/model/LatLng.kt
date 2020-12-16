package com.dom_broks.hireme.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LatLng(
    var latitude: Double? = null,
    var longitude: Double? = null
) : Parcelable
