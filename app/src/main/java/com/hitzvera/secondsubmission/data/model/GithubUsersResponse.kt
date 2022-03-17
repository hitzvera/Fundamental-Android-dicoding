package com.hitzvera.secondsubmission.data.model

import com.google.gson.annotations.SerializedName
/*
    data yang dibutuhkan yaitu
    login(username)
    follower dan following (dalam integer)
    beserta urlnya agar bisa ditampilkan di dalam fragment
 */
data class GithubUsersResponse(
	@field:SerializedName("items")
	val items: ArrayList<ItemsItem>
)

data class ItemsItem(
	// hanya untuk yang ditampilkan di recyclerview
	// dan yang memang yang saya butuhkan cuma login(username)
	// avatar, dan id (sebagai pembeda saja)
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("html_url")
	val htmlUrl: String,

)
