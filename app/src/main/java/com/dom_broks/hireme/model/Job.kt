package com.dom_broks.hireme.model


data class Job
    (
    var Id: String? = null,
    var Title: String? = null,
    var Salary: String? = null,
    var Location: LatLng? = null,
    var Experience: String? = null,
    var Company: String? = null,
    var Type: String? = null,
    var Image: String? = null
) {
    fun toMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["Id"] = this.Id!!
        map["Title"] = this.Title!!
        map["Company"] = this.Company!!
        map["Salary"] = this.Salary!!
        map["Type"] = this.Type!!
        map["Experience"] = this.Experience!!
        map["Location"] = this.Location!!
        map["Image"] = this.Image!!
        return map
    }
}