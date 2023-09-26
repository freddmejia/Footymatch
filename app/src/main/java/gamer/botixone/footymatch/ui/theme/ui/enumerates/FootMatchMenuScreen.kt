package gamer.botixone.footymatch.ui.theme.ui.enumerates

import androidx.annotation.StringRes
import gamer.botixone.footymatch.R
enum class FootMatchMenuScreen(@StringRes val title: Int){
    Login(title = R.string.login),
    Register(title = R.string.register),
    ForgotPass(title = R.string.recover_account),
    MenuApp(title = R.string.app_name)
}