package com.hitzvera.secondsubmission.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hitzvera.secondsubmission.data.model.ItemsItem
import com.hitzvera.secondsubmission.databinding.ItemGithubUserBinding

class GithubUserAdapter : RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    private val list = ArrayList<ItemsItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<ItemsItem>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemGithubUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(githubUser: ItemsItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(githubUser)
            }

            Glide.with(itemView)
                .load(githubUser.avatarUrl)
                .into(binding.ivGithubUser)
            binding.tvUsername.text = githubUser.login
            binding.tvHtmlUrl.text = githubUser.htmlUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }

}