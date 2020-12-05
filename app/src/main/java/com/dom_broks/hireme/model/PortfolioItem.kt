package com.dom_broks.hireme.model

data class PortfolioItem(
    var Id: String? = "",
    var Title: String? = "",
    var Image: String? = null
) {
    fun toMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["Id"] = this.Id!!
        map["Title"] = this.Title!!
        map["Image"] =
            "https://firebasestorage.googleapis.com/v0/b/hire-me-2568d.appspot.com/o/PortfolioImagees%2Falvaro-reyes-qWwpHwip31M-unsplash.jpg?alt=media&token=f89415ee-b39d-47a0-b2de-0f66243e2875"
        return map
    }
}
