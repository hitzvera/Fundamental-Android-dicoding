package com.hitzvera.secondsubmission.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hitzvera.secondsubmission.R
import com.hitzvera.secondsubmission.databinding.FragmentFollowerFollowingBinding
import com.hitzvera.secondsubmission.ui.GithubUserAdapter

class FollowersFragment : Fragment(R.layout.fragment_follower_following) {

    private var _binding: FragmentFollowerFollowingBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var followersAdapter: GithubUserAdapter // reuse adapter that are the same
    private lateinit var username: String

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowerFollowingBinding.bind(view)

        followersAdapter = GithubUserAdapter()
        followersAdapter.notifyDataSetChanged()

        binding.apply {
            rvFollowerFollowing.setHasFixedSize(true)
            rvFollowerFollowing.layoutManager = LinearLayoutManager(activity)
            rvFollowerFollowing.adapter = followersAdapter
        }

        followersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)
        showLoading(true)
        followersViewModel.setListFollowers(username)
        followersViewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                showLoading(false)
                followersAdapter.setList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}