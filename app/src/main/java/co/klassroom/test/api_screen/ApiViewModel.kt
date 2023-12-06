package co.klassroom.test.api_screen

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.klassroom.test.Post
import co.klassroom.test.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class ViewState {
    Loading, Error, Success
}

typealias Posts = List<Post>

class ApiViewModel: ViewModel() {
    @VisibleForTesting val postsRepository = PostsRepository()

    private val _viewState = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _posts = MutableStateFlow<Posts>(emptyList())
    val posts: StateFlow<Posts> = _posts.asStateFlow()

    private var page: Int = 1

    init {
        fetchPosts()
    }

    fun loadMore(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ++page
            val posts = postsRepository.getPosts(page)
            val oldList = _posts.value
            val newList = ArrayList(oldList)
            newList.addAll(posts)
            _posts.value = newList
            onSuccess.invoke()
        }
    }

    private fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.value = postsRepository.getPosts()
            _viewState.value = ViewState.Success
        }
    }
}
