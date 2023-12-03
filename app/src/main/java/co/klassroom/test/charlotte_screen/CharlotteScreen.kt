package co.klassroom.test.charlotte_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.klassroom.test.R

@Preview
@Composable
fun ScreenPreview() {
    CharlotteScreen(onMenuClick = {})
}

@Composable
fun CharlotteScreen(onMenuClick: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        HeaderSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            onMenuClick = onMenuClick,
        )
        Box {
            Image(
                painter = painterResource(id = R.drawable.charlotte),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
            CharlotteText(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = 24.dp,
                        bottom = 6.dp,
                    ),
                text = stringResource(id = R.string.charlotte_perriands).toUpperCase(Locale.current),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Text(
                text = stringResource(id = R.string.rest_of_the_screen_should_be_black),
                color = Color.Red,
            )
        }
    }
}


