package co.klassroom.test.api_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.klassroom.test.R
import com.squareup.picasso.Picasso

class PostsAdapter(private val apiViewModel: ApiViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.iv_avatar)
        val tvUserName: TextView = view.findViewById(R.id.tv_user_name)
        val tvDate: TextView = view.findViewById(R.id.tv_date)
        val tvMessage: TextView = view.findViewById(R.id.tv_message)
        val ivPostPhoto: ImageView = view.findViewById(R.id.iv_post_photo)
    }
    class LastItemHolder(view: View): RecyclerView.ViewHolder(view) {
        val progressBar: View = view.findViewById(R.id.progress_in_the_end)
        val textInTheEnd: TextView = view.findViewById(R.id.text_in_the_end)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == apiViewModel.posts.value.size) {
            R.layout.the_last_item
        } else {
            R.layout.post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return if (viewType == R.layout.the_last_item) {
            LastItemHolder(view)
        } else {
            PostViewHolder(view)
        }
    }

    override fun getItemCount(): Int = apiViewModel.posts.value.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            bindRegularItem(holder, position)
        } else if (holder is LastItemHolder) {
            apiViewModel.loadMore(
                onSuccess = {
                    notifyItemInserted(position + 1)
                }
            )
            val isLabelVisible = apiViewModel.isNoMoreLabelVisible.value
            holder.textInTheEnd.visibility = if (isLabelVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
            holder.progressBar.visibility = if (!isLabelVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun bindRegularItem(holder: PostViewHolder, position: Int) {
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
    }
}
