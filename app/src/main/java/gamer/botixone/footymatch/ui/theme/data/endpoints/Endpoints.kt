package gamer.botixone.footymatch.ui.theme.data.endpoints

class Endpoints {
    companion object {
        const val api_version = "/api"
        //const val domainApi = "http://192.168.1.31:8000/"

        const val domainApi = "https://wearablehealth-tfm.com/"
        const val domainApiFitbit = "https://www.fitbit.com/"
        const val oauthFitbit = "/oauth2"

        //user
        const val login = "$api_version/login"
        const val logout = "$api_version/logout"
        const val register = "$api_version/register"

    }
}