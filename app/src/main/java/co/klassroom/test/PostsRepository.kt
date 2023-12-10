package co.klassroom.test

import androidx.annotation.VisibleForTesting
import kotlinx.serialization.json.Json

class PostsRepository {
    @VisibleForTesting val postsFetcher = PostsFetcher()

    var totalPages: Int? = null
        private set
    private val json = Json { ignoreUnknownKeys = true }

    fun getPosts(page: Int = 1): List<Post> {
        val postsStr = postsFetcher.getPosts(page)
        val endpointResponse = json.decodeFromString(EndpointResponse.serializer(), postsStr)
        totalPages = endpointResponse.totalPages
        return endpointResponse.posts
    }
}
