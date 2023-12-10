package co.klassroom.test.api_screen

import android.view.View
import android.widget.ImageView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
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

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val apiScreen: View = View.inflate(it, R.layout.api_screen, null)
            val recyclerView = apiScreen.findViewById<RecyclerView>(R.id.recycle_view)
            recyclerView.adapter = PostsAdapter(apiViewModel)

            val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context, DividerItemDecoration.VERTICAL
            )
            dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(it, R.drawable.rectangle)!!
            )
            recyclerView.addItemDecoration(dividerItemDecoration)

            apiScreen.findViewById<ImageView>(R.id.iv_menu)
                .setOnClickListener { onMenuClick.invoke() }

            apiScreen.findViewById<ConstraintLayout>(R.id.row_in_the_bottom)
                .visibility = View.GONE

            apiScreen
        },
        update = {
            val clProgress = it.findViewById<ConstraintLayout>(R.id.cl_progress)
            clProgress.visibility = if (
                viewState.value == ViewState.Loading && apiViewModel.posts.value.isEmpty()
            ) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val rowWithProgress = it.findViewById<ConstraintLayout>(R.id.row_in_the_bottom)
            rowWithProgress.visibility = if (
                viewState.value == ViewState.Loading && apiViewModel.posts.value.isNotEmpty()
            ) {
                View.VISIBLE
            } else {
                View.GONE
            }


        }
    )
}
