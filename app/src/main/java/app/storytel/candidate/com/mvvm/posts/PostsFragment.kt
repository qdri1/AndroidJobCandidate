package app.storytel.candidate.com.mvvm.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import app.storytel.candidate.com.adapter.PostsAdapter
import app.storytel.candidate.com.databinding.FragmentPostsBinding
import app.storytel.candidate.com.model.PostAndPhotoModel
import app.storytel.candidate.com.mvvm.abstract.AbstractFragment

class PostsFragment : AbstractFragment<FragmentPostsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsBinding
        get() = FragmentPostsBinding::inflate

    private val postsViewModel: PostsViewModel by viewModels()
    private val postsAdapter: PostsAdapter by lazy { PostsAdapter() }

    override fun viewPrepared() {
        setupAdapter()
        observeViewModel()
        postsViewModel.fetchPosts()
    }

    private fun setupAdapter() {
        postsAdapter.onItemClick = ::openPostDetailScreen
        binding.recyclerView.adapter = postsAdapter
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun observeViewModel() {
        postsViewModel.getPosts().observe(viewLifecycleOwner, Observer { posts ->
            postsAdapter.list = posts
            postsAdapter.notifyDataSetChanged()
        })
        postsViewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        postsViewModel.getErrorHandler().observe(viewLifecycleOwner, Observer { _ ->
            showErrorDialog { postsViewModel.fetchPosts() }
        })
    }

    private fun openPostDetailScreen(post: PostAndPhotoModel) {
        val action = PostsFragmentDirections.actionPostDetail(post)
        findNavController().navigate(action)
    }

}