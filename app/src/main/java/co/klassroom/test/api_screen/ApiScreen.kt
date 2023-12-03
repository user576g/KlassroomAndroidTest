package co.klassroom.test.api_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.klassroom.test.R

@Preview
@Composable
fun PreviewApi() {
    ApiScreen(onMenuClick = {})
}

@Composable
fun ApiScreen(onMenuClick: () -> Unit) {
    val apiViewModel: ApiViewModel = viewModel()
    val viewState = apiViewModel.viewState.collectAsState()
    val posts = apiViewModel.posts.collectAsState()

    Column {
        HeaderSection(onMenuClick = onMenuClick)

        if (viewState.value == ViewState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            AndroidView(
                modifier = Modifier.padding(horizontal = 12.dp),
                factory = {
                    val recyclerView = RecyclerView(it)
                    recyclerView.layoutManager = LinearLayoutManager(it)
                    recyclerView.adapter = PostsAdapter(posts.value)

                    val dividerItemDecoration = DividerItemDecoration(
                        recyclerView.context, DividerItemDecoration.VERTICAL
                    )
                    dividerItemDecoration.setDrawable(
                        ContextCompat.getDrawable(it, R.drawable.rectangle)!!
                    )
                    recyclerView.addItemDecoration(dividerItemDecoration)

                    recyclerView
                }
            )
        }
    }
}
