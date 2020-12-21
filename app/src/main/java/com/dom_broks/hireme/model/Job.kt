package com.dom_broks.hireme.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Job
    (
    var Id: String? = null,
    var Title: String? = null,
    var Description: String? = null,
    var Salary: String? = null,
    var Location: LatLng? = null,
    var Experience: String? = null,
    var Company: String? = null,
    var Type: String? = null,
    var Image: String? = null
) : Parcelable {
    fun toMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["Id"] = this.Id!!
        map["Title"] = this.Title!!
        map["Description"] = this.Description!!
        map["Company"] = this.Company!!
        map["Salary"] = this.Salary!!
        map["Type"] = this.Type!!
        map["Experience"] = this.Experience!!
        map["Location"] = this.Location!!
        map["Image"] = this.Image!!
        return map
    }
}