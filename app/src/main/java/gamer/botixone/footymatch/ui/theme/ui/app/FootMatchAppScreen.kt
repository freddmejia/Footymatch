    package gamer.botixone.footymatch.ui.theme.ui.app

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gamer.botixone.footymatch.R
import gamer.botixone.footymatch.ui.theme.ui.account.ForgotPasswordScreen
import gamer.botixone.footymatch.ui.theme.ui.account.LoginScreen
import gamer.botixone.footymatch.ui.theme.ui.account.RegisterScreen
import gamer.botixone.footymatch.ui.theme.ui.enumerates.FootMatchMenuScreen
import gamer.botixone.footymatch.ui.theme.ui.viewmodel.LoginViewModel

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FootMachScreen(
    navController: NavHostController = rememberNavController()
){
    val TAG = "FootMachScreen";
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = FootMatchMenuScreen.valueOf(
        backStackEntry?.destination?.route ?: FootMatchMenuScreen.Login.name
    )
    Scaffold(
        topBar = {
            ToolBarFootMatch(
                currentScreen = currentScreen,
                navigationBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){
        NavHost(
            navController = navController,
            startDestination = FootMatchMenuScreen.Login.name,
            modifier = Modifier.padding(it)
        ){
            composable(
                route = FootMatchMenuScreen.Login.name
            ){
                LoginScreen(
                    registerOnClicked = {
                        Log.e(TAG, "FootMachScreen: register" )
                        navController.navigate(FootMatchMenuScreen.Register.name)
                    },
                    forgotPassOnClicked = {
                        Log.e(TAG, "FootMachScreen: register 11" )
                        navController.navigate(FootMatchMenuScreen.ForgotPass.name)
                    }
                )
            }
            composable(
                route = FootMatchMenuScreen.Register.name
            ){
                Log.e(TAG, "FootMachScreen: register 22" )
                RegisterScreen(LoginViewModel())
            }
            composable(
                route = FootMatchMenuScreen.ForgotPass.name
            ){
                ForgotPasswordScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarFootMatch(
    currentScreen: FootMatchMenuScreen,
    navigationBack: Boolean,
    navigateUp: ()-> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = colorResource(id = R.color.aqua_island),
        ),
        modifier = modifier,
        navigationIcon = {
            if (navigationBack) {
                IconButton(onClick =  navigateUp ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )

}