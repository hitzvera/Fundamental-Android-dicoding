package com.htizvera.submission1fundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.htizvera.submission1fundamental.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser

        binding.tvItemUsername.text = user.username
        binding.tvItemName.text = user.name

        Glide.with(this)
            .load(user.photo) // URL Gambar
            .circleCrop() // Mengubah image menjadi lingkaran
            .into(binding.imgItemPhoto) // imageView mana yang akan diterapkan

        binding.tvItemProfession.text = user.profession
        binding.tvItemFollower.text = user.follower
        binding.tvItemFollowing.text = user.following
        binding.tvItemCompany.text = user.company
        binding.tvItemLocation.text = user.location
        binding.tvItemRepository.text = user.repository

    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}