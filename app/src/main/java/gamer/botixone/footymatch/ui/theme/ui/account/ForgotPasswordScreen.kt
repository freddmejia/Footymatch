package gamer.botixone.footymatch.ui.theme.ui.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gamer.botixone.footymatch.R

@Preview
@Composable
fun ForgotPasswordScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                all = 10.dp
            )){
        ForgotPassword(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
    }
}

@Composable
fun ForgotPassword(
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        EmailField(
            email = "",
            onTextFieldChange = {}
        )
        SimpleButtonLogin(
            text = stringResource(id = R.string.next_recover_account),
            buttonColors =
            ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.cello),
                disabledBackgroundColor = colorResource(id = R.color.wedgewood),
                contentColor = Color.White,
                disabledContentColor = Color.White,
            )
        ) {

        }
    }

}
