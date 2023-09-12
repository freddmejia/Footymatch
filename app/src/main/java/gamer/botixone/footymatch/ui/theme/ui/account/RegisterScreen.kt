package gamer.botixone.footymatch.ui.theme.ui.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import  gamer.botixone.footymatch.R
import gamer.botixone.footymatch.ui.theme.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun RegisterScreen(loginViewModel: LoginViewModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)){
        Register(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
            viewModel = loginViewModel
        )
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: LoginViewModel) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val repeatPassword: String by viewModel.repeatPassword.observeAsState(initial = "")
    val coroutineScope = rememberCoroutineScope()
    val isCreatingAccount: Boolean by viewModel.isNewUser.observeAsState(initial = true)

    if (!isCreatingAccount) {
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        return
    }
    Column(modifier = modifier) {
        NameField(
            name = name,
            onTextFieldChange = {
                viewModel.onRegisterChange(
                    name = it,
                    email = email,
                    password = password,
                    repeatPassword = repeatPassword
                )
            }
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        EmailField(
            email = email,
            onTextFieldChange = {
                viewModel.onRegisterChange(
                    name = name,
                    email = it,
                    password = password,
                    repeatPassword = repeatPassword,
                )
            }
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        PasswordField(
            password = password,
            onTextFieldChange = {
                viewModel.onRegisterChange(
                    name = name,
                    email = email,
                    password = it,
                    repeatPassword = repeatPassword
                )
            }
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        RePasswordField(
            repeatPassword = repeatPassword,
            onTextFieldChange = {
                viewModel.onRegisterChange(
                    name = name,
                    email = email,
                    password = password,
                    repeatPassword = it
                )
            }
        )
        Spacer(modifier = Modifier.padding(bottom = 25.dp))
        SimpleButtonLogin(
            text = stringResource(id = R.string.create_account),
            buttonColors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.selective_yellow),
                disabledBackgroundColor = colorResource(id = R.color.sky_cc),
                contentColor = Color.White,
                disabledContentColor = Color.White,
            ),

        ){
            coroutineScope.launch {
                viewModel.createAccount()
            }
        }
    }
}

@Composable
fun NameField(name: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        placeholder = { Text(text =  stringResource(id = R.string.write_name)) },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.text_field_text_color),
            backgroundColor = colorResource(id = R.color.text_field_background),
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,

            )
    )
}

@Composable
fun RePasswordField(repeatPassword: String, onTextFieldChange: (String) -> Unit){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = repeatPassword,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.repeat_write_password))
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.text_field_text_color),
            backgroundColor = colorResource(id = R.color.text_field_background),
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '*'),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        }
    )
}

