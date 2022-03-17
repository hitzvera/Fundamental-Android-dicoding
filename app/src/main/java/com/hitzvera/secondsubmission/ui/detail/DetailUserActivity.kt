package com.hitzvera.secondsubmission.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hitzvera.secondsubmission.R
import com.hitzvera.secondsubmission.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username) // sending 'key' to get follower

        detailUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        detailUserViewModel.setGithubUserDetail(username!!)
        showLoading(true)
        detailUserViewModel.getGithubUserDetail().observe(this) {
            if (it != null) {
                showLoading(false)
                detailUserBinding.apply {
                    tvUsername.text = it.login
                    // been trying with default value using
                    // tvName.text = if(it.name.isNotEmpty()) it.name else "Name has not been Defined" but does not work
                    // any solution?
                    tvName.text = it.name
                    tvFollower.text = "${it.followers} Follower"
                    tvFollowing.text = "${it.following} Following"
                    tvLocation.text = it.location
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .into(ivUserAvatar)
                }
            }
        }
        val sectionPagerAdapter = SectionPagerAdapter(this, bundle)
        detailUserBinding.apply {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLE[position])
            }.attach()
        }

        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            detailUserBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailUserBinding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.tab1, R.string.tab2)
    }
}