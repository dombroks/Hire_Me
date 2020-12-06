package com.dom_broks.hireme.model


data class Experience
    (
    var Id: String? = null,
    var Title: String? = null,
    var Duration: String? = null,
    var From: String? = null,
    var To: String? = null,
    var Place: String? = null,
    var Image: String? = null


) {
    fun toMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["id"] = this.Id!!
        map["title"] = this.Title!!
        map["place"] = this.Place!!
        map["duration"] = this.Duration!!
        map["from"] = this.From!!
        map["to"] = this.To!!
        map["image"] =
            "https://firebasestorage.googleapis.com/v0/b/hire-me-2568d.appspot.com/o/ExperienceImages%2Ffacebook.jpeg?alt=media&token=cda8e8b2-c5ed-49f1-a452-8c7ddea1ddfa"
        return map
    }
}


