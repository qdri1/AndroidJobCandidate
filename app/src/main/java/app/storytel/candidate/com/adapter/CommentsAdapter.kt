package app.storytel.candidate.com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.databinding.CommentItemBinding
import app.storytel.candidate.com.model.CommentModel

class CommentsAdapter(
        var list: List<CommentModel> = emptyList()
) : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.title.text = name
                binding.description.text = body
                binding.email.text = email
            }
        }
    }

    inner class CommentsViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root)

}