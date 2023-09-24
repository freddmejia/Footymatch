package gamer.botixone.footymatch.ui.theme.utils

import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONObject

class Utils {
    companion object {

        fun <T> errorResult(message: String,errorBody: ResponseBody? = null): Result<T> {
            //Timber.d(message)
            Log.e("", "coroutines errorResult: "+errorBody.toString() )

            var mess_d = message
            if (errorBody != null) {
                val json = JSONObject(errorBody?.string())
                mess_d = json.getString("message")
            }
            Log.e("", "coroutines errorResult: "+mess_d.toString() )
            return Result.Error(mess_d)
        }
    }
}