package co.klassroom.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName("user_name")
    val userName: String,

    @SerialName("user_pic")
    val userPictureUrl: String,

    val message: String,

    @SerialName("photo")
    val photoUrl: String? = null,

    val date: String
)

@Serializable
data class EndpointResponse(
    @SerialName("total_pages")
    val totalPages: Int,

    val posts: List<Post>
)
