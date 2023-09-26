package gamer.botixone.footymatch.ui.theme.presentation.account

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gamer.botixone.footymatch.ui.theme.data.model.User
import gamer.botixone.footymatch.ui.theme.domain.user.AccountUseCase
import gamer.botixone.footymatch.ui.theme.utils.CompositionObj
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import gamer.botixone.footymatch.ui.theme.utils.*
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase
): ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _isNewUser = MutableLiveData<Boolean>()
    val isNewUser: LiveData<Boolean> = _isNewUser

    private val _compositionLogin = MutableStateFlow<Result<CompositionObj<User, String>>>(Result.Empty)
    val compositionLogin : StateFlow<Result<CompositionObj<User, String>>> = _compositionLogin

    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn


    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
    }

    fun onRegisterChange(name: String, email: String, password: String, repeatPassword: String) {
        _name.value = name
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword


    }

    fun launchRegister(isNewUser: Boolean){
        _isNewUser.value = isNewUser
    }

    fun onRegisterChange(name: String, email: String, password: String) {
        _name.value = name
        _email.value = email
        _password.value = password
    }

    fun createAccount() = viewModelScope.launch {
        _isNewUser.value = false
        _compositionLogin.value = Result.Loading
        _compositionLogin.value = accountUseCase.register(username = _name.value!!, email =  _email.value!!, password = _password.value!!, password2 = _repeatPassword.value!!)
        _isNewUser.value = true
    }

    fun LoginAccount() = viewModelScope.launch {
        _isNewUser.value = false
        _compositionLogin.value = Result.Loading
        _compositionLogin.value = accountUseCase.login(_email.value!!, _password.value!!)
        _isNewUser.value = true
    }

    fun setCompositionLigin(result: Result<CompositionObj<User, String>>) {
        _compositionLogin.value = result
    }
    fun setIsLoggedIn(isLoggedI: Boolean) {
        _isLoggedIn.value = isLoggedI
    }
}

