package gamer.botixone.footymatch.ui.theme.data.model_serialized

import com.google.gson.annotations.SerializedName
import gamer.botixone.footymatch.ui.theme.data.model.User

class UserResponseApi {
    @SerializedName("user") var user: User = User()
    @SerializedName("message") var message: String = ""
}