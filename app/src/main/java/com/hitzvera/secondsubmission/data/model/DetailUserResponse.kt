package com.hitzvera.secondsubmission.data.model

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    // for every detail key or attribute
    // https://api.github.com/users/{username}

    val login: String,
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("followers_url")
    val followersUrl: String,

    @SerializedName("following_url")
    val followingUrl: String,

    val name: String,
    val location: String,
    val followers: Int,
    val following: Int
)
