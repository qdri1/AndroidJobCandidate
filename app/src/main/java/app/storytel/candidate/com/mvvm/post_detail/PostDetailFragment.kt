package app.storytel.candidate.com.mvvm.post_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.storytel.candidate.com.R
import app.storytel.candidate.com.adapter.CommentsAdapter
import app.storytel.candidate.com.databinding.FragmentDetailsBinding
import app.storytel.candidate.com.mvvm.abstract.AbstractFragment
import com.bumptech.glide.Glide

class DetailsFragment : AbstractFragment<FragmentDetailsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    private val postDetailViewModel: PostDetailViewModel by viewModels()
    private val commentsAdapter: CommentsAdapter by lazy { CommentsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = DetailsFragmentArgs.fromBundle(arguments ?: Bundle())
        postDetailViewModel.post = args.post.copy()
    }

    override fun viewPrepared() {
        setupToolbar()
        setupPostData()
        setupCommentsAdapter()
        observeViewModel()
        postDetailViewModel.fetchComments()
    }

    private fun setupToolbar() {
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_arrow)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun setupPostData() {
        Glide.with(this)
                .load(postDetailViewModel.post.photoUrl)
                .placeholder(R.drawable.no_image)
                .into(binding.backdrop)
        binding.title.text = postDetailViewModel.post.postTitle
        binding.body.text = postDetailViewModel.post.postBody
    }

    private fun setupCommentsAdapter() {
        binding.recyclerView.adapter = commentsAdapter
    }

    private fun observeViewModel() {
        postDetailViewModel.getComments().observe(viewLifecycleOwner, Observer { comments ->
            commentsAdapter.list = comments
            commentsAdapter.notifyDataSetChanged()
        })
        postDetailViewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        postDetailViewModel.getErrorHandler().observe(viewLifecycleOwner, Observer { _ ->
            showErrorDialog { postDetailViewModel.fetchComments() }
        })
    }

}