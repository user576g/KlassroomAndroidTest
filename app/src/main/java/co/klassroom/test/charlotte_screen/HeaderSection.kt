package co.klassroom.test.charlotte_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.klassroom.test.R

@Preview
@Composable
fun HeaderPreview() {
    HeaderSection(onMenuClick = {})
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier, onMenuClick: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "menu",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable(onClick = onMenuClick)
        )
        Text(
            text = stringResource(id = R.string.test_android).toUpperCase(Locale.current),
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
        )
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .clickable {  }
        )
    }
}
