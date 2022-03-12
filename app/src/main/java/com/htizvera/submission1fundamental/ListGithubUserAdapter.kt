package com.htizvera.submission1fundamental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListGithubUserAdapter(private val listGithubUser: ArrayList<GithubUser>): RecyclerView.Adapter<ListGithubUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvProfesi: TextView = itemView.findViewById(R.id.tv_item_profesi)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_github_user, parent, false)
        return ListViewHolder(view)
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(username, name, image, profession, _, _, _, _, _) = listGithubUser[position]
        holder.tvUsername.text = username
        holder.tvName.text = name
        Glide.with(holder.itemView.context)
            .load(image)
            .circleCrop()
            .into(holder.imgPhoto)
        holder.tvProfesi.text = profession

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGithubUser[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int  = listGithubUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: GithubUser)
    }


}
