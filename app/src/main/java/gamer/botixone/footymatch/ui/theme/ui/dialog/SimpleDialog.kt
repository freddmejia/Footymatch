package gamer.botixone.footymatch.ui.theme.ui.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import gamer.botixone.footymatch.R

@Composable
fun SimpleDialog(onDismis: () -> Unit, onConfirm: ()-> Unit, title: Int, message: String){
    AlertDialog(
        onDismissRequest = { onDismis() },
        confirmButton ={
            TextButton(
                onClick = {
                    onConfirm()
                }) {
                Text(text = stringResource(id = R.string.accept))
            }
        },
        title = {
            Text(text = stringResource(id = title))
        },
        text = {
            Text(text = message)
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismis()
                }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )


}