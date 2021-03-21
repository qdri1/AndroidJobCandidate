package app.storytel.candidate.com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.PostItemBinding
import app.storytel.candidate.com.model.PostAndPhotoModel
import com.bumptech.glide.Glide

class PostsAdapter(
        var list: List<PostAndPhotoModel> = emptyList()
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var onItemClick: (PostAndPhotoModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.title.text = postTitle
                binding.body.text = postBody
                Glide.with(itemView)
                        .load(photoThumbnailUrl)
                        .placeholder(R.drawable.no_image)
                        .into(binding.image)
                itemView.setOnClickListener {
                    onItemClick(this)
                }
            }
        }
    }

    inner class PostsViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

}