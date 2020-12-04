package com.dom_broks.hireme.model

data class PortfolioItem(
    var Title: String? = null,
    var Image: String? = null
) {
    fun toMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["Title"] = this.Title!!
        map["Image"] =
            " https://firebasestorage.googleapis.com/v0/b/hire-me-2568d.appspot.com/o/facebook.png?alt=media&token=f2cd0c06-69de-4a32-892b-ff2d502fb378"
        return map
    }
}
