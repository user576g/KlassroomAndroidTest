package co.klassroom.test.charlotte_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.klassroom.test.R

@Preview
@Composable
fun TextPreview() {
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
                    start = 16.dp,
                    bottom = 6.dp,
                ),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales laoreet commodo.",
        )
    }
}

@Composable
fun CharlotteText(
    modifier: Modifier = Modifier,
    text: String,
) {
    val rows = text.charlotteSplit()
    val len = text.length
    val fontSize: TextUnit = when {
        len < 50 -> 22.sp
        len < 100 -> 20.sp
        else -> 18.sp
    }

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            fun appendText(text: String) {
                withStyle(
                    style = SpanStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        background = Color.White
                    )
                ) {
                    append(text)
                }
            }

            fun appendSpace() {
                withStyle(
                    style = ParagraphStyle()
                ) {
                    withStyle(SpanStyle(fontSize = 6.sp)) {
                        append(" ")
                    }

                }
            }

            rows.subList(0, rows.size - 1).forEach {
                appendText(it)
                appendSpace()
            }
            appendText(rows.last())
        }
    )
}

private fun String.charlotteSplit(): List<String> {
    val words: List<String> = this.split(" ")
    val result = ArrayList<String>()
    val len = this.length
    var charsInRow: Int = when {
        len < 50 -> 20
        len < 100 -> 30
        else -> 35
    }

    var iLineHas = 0
    val sb = StringBuilder()
    for(word in words) {
        if (iLineHas + word.length <= charsInRow) {
            sb.append(word)
            iLineHas += word.length
            sb.append(" ")
            iLineHas += 1
        } else {
            result.add(sb.toString())
            sb.clear()
            charsInRow = iLineHas - 1
            iLineHas = 0

            sb.append(word)
            iLineHas += word.length
            sb.append(" ")
            iLineHas += 1
        }
    }

    result.add(sb.toString())
    return result
}
