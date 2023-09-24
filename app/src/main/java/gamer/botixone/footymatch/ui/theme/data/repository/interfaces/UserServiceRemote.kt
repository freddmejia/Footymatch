package gamer.botixone.footymatch.ui.theme.data.repository.interfaces

import gamer.botixone.footymatch.ui.theme.data.endpoints.Endpoints
import gamer.botixone.footymatch.ui.theme.data.model_serialized.LogoutdDResponseApi
import gamer.botixone.footymatch.ui.theme.data.model_serialized.UserResponseApi
import retrofit2.http.Body
import retrofit2.http.POST

interface UserServiceRemote {
    @POST(Endpoints.login)
    suspend fun loginUser(@Body requestBody: Map<String,String>): retrofit2.Response<UserResponseApi>
    @POST(Endpoints.register)
    suspend fun registerUser(@Body requestBody: Map<String,String>): retrofit2.Response<UserResponseApi>
    @POST(Endpoints.logout)
    suspend fun logout(@Body requestBody: Map<String,String>): retrofit2.Response<LogoutdDResponseApi>
}