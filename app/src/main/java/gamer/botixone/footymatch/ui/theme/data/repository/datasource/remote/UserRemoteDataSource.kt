package gamer.botixone.footymatch.ui.theme.data.repository.datasource.remote

import gamer.botixone.footymatch.ui.theme.data.repository.interfaces.UserServiceRemote
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserServiceRemote
) {
    suspend fun login(requestBody: MutableMap<String, String>) = userService.loginUser(requestBody)
    suspend fun registerUser(requestBody: MutableMap<String, String>) = userService.registerUser(requestBody)
    suspend fun logout(requestBody: MutableMap<String, String>) = userService.logout(requestBody)

}