package gamer.botixone.footymatch.ui.theme.ui.account


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import gamer.botixone.footymatch.R
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import gamer.botixone.footymatch.ui.theme.data.model.User
import gamer.botixone.footymatch.ui.theme.presentation.account.AccountViewModel
import gamer.botixone.footymatch.ui.theme.ui.dialog.SimpleDialog
import gamer.botixone.footymatch.ui.theme.ui.menu.MenuAppFootyMatchScreen
import gamer.botixone.footymatch.ui.theme.ui.viewmodel.LoginViewModel
import gamer.botixone.footymatch.ui.theme.utils.CompositionObj
import gamer.botixone.footymatch.ui.theme.utils.Result



@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: AccountViewModel = hiltViewModel(),
    registerOnClicked: () -> Unit,
    forgotPassOnClicked: () -> Unit,

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)){

        Login(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            loginViewModel = viewModel,
            registerOnClicked = { registerOnClicked() },
            forgotPassOnClicked = { forgotPassOnClicked() }
        )
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Login(
    modifier: Modifier,
    loginViewModel: AccountViewModel,
    registerOnClicked: () -> Unit,
    forgotPassOnClicked: () -> Unit
) {

    val TAG = "Login"
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val resultAccount by loginViewModel.compositionLogin.collectAsState(initial = Result.Empty)
    var showDialog by rememberSaveable { mutableStateOf(false) }

    when(resultAccount){
        is Result.Success<CompositionObj<User, String>> -> {
            //here
            loginViewModel.setIsLoggedIn(isLoggedI = true)
            /*loginViewModel.setIsLoggedIn(
                isLoggedI = true
            )*/
            /*Box(modifier = Modifier.fillMaxSize()){
                Text(text = "login succed")
            }*/
        }
        is Result.Loading-> {
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        /*is Result.Error-> {
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = (resultAccount as Result.Error).error)
            }
        }*/
        else -> {
            if (resultAccount is Result.Error){
                Log.e(TAG, "Login: error login", )
                showDialog = true
            }
            if (showDialog){
                SimpleDialog(
                    onDismis = {
                        showDialog = false
                        loginViewModel.setCompositionLigin(
                            result = Result.Empty
                        )
                               },
                    onConfirm = {
                        showDialog = false
                        loginViewModel.setCompositionLigin(
                            result = Result.Empty
                        )
                                },
                    title = R.string.error,
                    message = (resultAccount as Result.Error).error
                )
            }
            else {
                LoginForm(
                    modifier = modifier,
                    email = email,
                    password = password,
                    loginViewModel = loginViewModel,
                    registerOnClicked = registerOnClicked,
                    forgotPassOnClicked = forgotPassOnClicked
                )
            }
        }
    }

}

@Composable
fun LoginForm(
    modifier: Modifier,
    email: String,
    password: String,
    loginViewModel: AccountViewModel,
    registerOnClicked: () -> Unit,
    forgotPassOnClicked: () -> Unit
) {
    Column(modifier = modifier) {
        HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
        EmailField(
            email = email,
            onTextFieldChange = {
                loginViewModel.onLoginChange(email = it, password = password)
            }
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        PasswordField(
            password = password,
            onTextFieldChange = {
                loginViewModel.onLoginChange(email = email, password = it)
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        SimpleButtonLogin(
            text = stringResource(id = R.string.login_button),
            buttonColors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.cello),
                disabledBackgroundColor = colorResource(id = R.color.wedgewood),
                contentColor = Color.White,
                disabledContentColor = Color.White,
            )
        ) {
            loginViewModel.LoginAccount()
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ForgotPasswordText(forgotPassOnClicked = {
                forgotPassOnClicked()
            })
            NewUserText(registerOnClicked = {
                registerOnClicked()
            })
        }

    }
}
@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.football_img) ,
        contentDescription ="header",
        modifier = modifier.fillMaxHeight(0.5f)
    )
}

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        placeholder = { Text(text =  stringResource(id = R.string.write_email)) },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF494949),
            backgroundColor = Color(0xFB1AFAF),
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,

            )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text(text =  stringResource(id = R.string.write_password)) },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF494949),
            backgroundColor = Color(0xFB1AFAF),
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

@Composable
fun SimpleButtonLogin(text: String, buttonColors: ButtonColors, simpleOnClickAction: () -> Unit) {
    Button(onClick = { simpleOnClickAction() },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        colors = buttonColors
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ForgotPasswordText(forgotPassOnClicked: () -> Unit) {
    Text(
        text = stringResource(id = R.string.forgot_password),
        modifier = Modifier.clickable {
            forgotPassOnClicked()
        }
    )
}


@Composable
fun NewUserText(registerOnClicked: () -> Unit) {
    Text(
        text = stringResource(id = R.string.new_user),
        modifier = Modifier.clickable {
            registerOnClicked()
        }
    )
}