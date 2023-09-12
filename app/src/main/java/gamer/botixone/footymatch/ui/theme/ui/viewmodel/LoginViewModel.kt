package gamer.botixone.footymatch.ui.theme.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
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
        delay(2000)
        _isNewUser.value = true
    }
}