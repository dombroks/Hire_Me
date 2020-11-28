package com.dom_broks.hireme.model


data class Experience
    (
    var Title: String? = null,
    var Duration: String? = null,
    var From: String? = null,
    var To: String? = null,
    var Place: String? = null,
    var Image: String? = null


) {
    fun toMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["title"] = this.Title!!
        map["place"] = this.Place!!
        map["duration"] = this.Duration!!
        map["from"] = this.From!!
        map["to"] = this.To!!
        map["image"] =
            "https://firebasestorage.googleapis.com/v0/b/hire-me-2568d.appspot.com/o/ProfileImages%2FimgDGqys82RsEW7tkmVyaVM8jPzJFo1?alt=media&token=a131134b-4e57-4c41-b92b-ada0e46253cb"
        return map
    }
}


