package co.klassroom.test

import okhttp3.OkHttpClient
import okhttp3.Request

class PostsFetcher {
    private val client = OkHttpClient()

    fun getPosts(page: Int = 1): String {
        val request: Request = Request.Builder()
            .url("http://citymani.ezrdv.org/main/test?page=$page")
            .build()

        client.newCall(request)
            .execute()
            .use { response -> return response.body!!.string() }
    }
}