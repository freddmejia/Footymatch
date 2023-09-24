package gamer.botixone.footymatch.ui.theme.domain.user

import android.util.Log
import gamer.botixone.footymatch.ui.theme.data.model.User
import gamer.botixone.footymatch.ui.theme.data.repository.datasource.remote.UserRemoteDataSource
import gamer.botixone.footymatch.ui.theme.utils.CompositionObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import gamer.botixone.footymatch.ui.theme.utils.*
import gamer.botixone.footymatch.ui.theme.utils.Utils.Companion.errorResult

class AccountUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {

    suspend fun login(email: String,password: String): Result<CompositionObj<User, String>> {
        return withContext(Dispatchers.Default){
            try {
                val requestBody: MutableMap<String, String> = HashMap()
                requestBody["email"] = email
                requestBody["password"] = password
                val response = userRemoteDataSource.login(requestBody = requestBody)
                val body = response.body()
                if (body != null) {
                    var user = response.body()!!.user
                    val ab = CompositionObj<User,String>(user, response.body()!!.message)
                    Result.Success(ab)
                }
                else{
                    errorResult( message = "",errorBody = response.errorBody()!!)
                }
            }catch (e: Exception){
                errorResult(message = e.message ?: e.toString())
            }
        }
    }

    suspend fun logout(user_id: String): Result<CompositionObj<String, String>> {
        return withContext(Dispatchers.Default){
            try {
                val requestBody: MutableMap<String, String> = HashMap()
                requestBody["user_id"] = user_id
                val response = userRemoteDataSource.logout(requestBody = requestBody)
                val body = response.body()
                if (body != null) {
                    var message = response.body()!!.message
                    val ab = CompositionObj<String,String>(message, response.body()!!.message)
                    Result.Success(ab)
                }
                else{
                    errorResult( message = "",errorBody = response.errorBody()!!)
                }
            }catch (e: Exception){
                errorResult(message = e.message ?: e.toString())
            }
        }
    }

    suspend fun register(username: String,email: String,password: String,password2: String): Result<CompositionObj<User, String>> {
        return withContext(Dispatchers.Default){
            try {
                val requestBody: MutableMap<String, String> = HashMap()
                requestBody["name"] = username
                requestBody["email"] = email
                requestBody["password"] = password
                requestBody["c_password"] = password2
                val response = userRemoteDataSource.registerUser(requestBody = requestBody)
                val body = response.body()
                if (body != null) {
                    val bod = response.body()!!
                    val ab = CompositionObj<User,String>(bod.user, response.body()!!.message)
                    Result.Success(ab)
                }
                else{
                    errorResult( message = "",errorBody = response.errorBody()!!)
                }
            }catch (e: Exception){
                errorResult(message = e.message ?: e.toString())
            }
        }
    }

}