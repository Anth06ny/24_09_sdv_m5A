package com.example.a24_09_sdv_m5a.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.a24_09_sdv_m5a.ui.theme._24_09_sdv_m5ATheme

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyErrorPreview() {
    _24_09_sdv_m5ATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                //Je mets 2 versions pour tester avec et sans message d'erreur
                MyError(errorMessage = "Avec message d'erreur")
                Text("Sans erreur : ")
                MyError(errorMessage = "")
                Text("----------")
            }

        }
    }
}

//Le composant est réutilisable avec n'importe quelle chaine de caractère
@Composable
fun MyError(
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    //permet d'afficher / masquer l'erreur avec une animation
    AnimatedVisibility(!errorMessage.isNullOrBlank()) {
        Text(
            text = errorMessage ?: "",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onError,
            modifier = modifier.fillMaxWidth().background(MaterialTheme.colorScheme.error)
        )
    }
}