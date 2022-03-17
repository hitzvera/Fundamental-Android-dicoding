package com.hitzvera.secondsubmission.api

import com.hitzvera.secondsubmission.data.model.DetailUserResponse
import com.hitzvera.secondsubmission.data.model.GithubUsersResponse
import com.hitzvera.secondsubmission.data.model.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserApi {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getGithubUser(
        @Query("q") query: String
    ): Call<GithubUsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailGithubUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getGithubUserFollowers(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getGithubUserFollowing(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
}