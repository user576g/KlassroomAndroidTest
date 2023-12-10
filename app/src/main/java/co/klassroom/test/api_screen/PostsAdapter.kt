package co.klassroom.test.api_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.klassroom.test.R
import com.squareup.picasso.Picasso

class PostsAdapter(private val apiViewModel: ApiViewModel): RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.iv_avatar)
        val tvUserName: TextView = view.findViewById(R.id.tv_user_name)
        val tvDate: TextView = view.findViewById(R.id.tv_date)
        val tvMessage: TextView = view.findViewById(R.id.tv_message)
        val ivPostPhoto: ImageView = view.findViewById(R.id.iv_post_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = apiViewModel.posts.value.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = apiViewModel.posts.value[position]

        with(holder) {
            tvUserName.text = post.userName
            tvDate.text = post.date
            tvMessage.text = post.message

            Picasso.get()
                .load(post.userPictureUrl)
                .error(R.drawable.profile_picture)
                .into(ivAvatar)

            val photoUrl = post.photoUrl
            if (photoUrl == null) {
                ivPostPhoto.visibility = View.GONE
            } else {
                ivPostPhoto.visibility = View.VISIBLE
                Picasso.get()
                    .load(photoUrl)
                    .into(ivPostPhoto)
            }
        }

        if (apiViewModel.posts.value.size == position + 1) {
            apiViewModel.loadMore(
                onSuccess = {
                    notifyItemInserted(position + 1)
                }
            )
        }
    }
}
