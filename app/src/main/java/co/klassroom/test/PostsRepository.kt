package co.klassroom.test

import androidx.annotation.VisibleForTesting
import kotlinx.serialization.json.Json

class PostsRepository {
    @VisibleForTesting val postsFetcher = PostsFetcher()
    private val json = Json { ignoreUnknownKeys = true }

    fun getPosts(page: Int = 1): List<Post> {
        val postsStr = postsFetcher.getPosts(page)
        val endpointResponse = json.decodeFromString(EndpointResponse.serializer(), postsStr)
        return endpointResponse.posts
    }
}
