package gamer.botixone.footymatch.ui.theme.data.model

import org.json.JSONObject

class User (
    var id: Int,
    var name: String,
    var email: String,
    var token_firebase: String? = null,
    var image: String? = null,
    var code_qr: String? = null,
    var token: String,
    var password: String = ""
) {
    constructor() : this(0,"","","","","","")
    constructor(jsonObject: JSONObject): this(
        jsonObject.getInt("id"),
        jsonObject.getString("name"),
        jsonObject.getString("email"),
        jsonObject.getString("token_firebase"),
        jsonObject.getString("image"),
        jsonObject.getString("code_qr"),
        jsonObject.getString("token")
    )
}