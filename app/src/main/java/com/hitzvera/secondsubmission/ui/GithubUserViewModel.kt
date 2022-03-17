package com.hitzvera.secondsubmission.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitzvera.secondsubmission.api.RetrofitInstance
import com.hitzvera.secondsubmission.data.model.GithubUsersResponse
import com.hitzvera.secondsubmission.data.model.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserViewModel : ViewModel() {

    var githubUsers = MutableLiveData<ArrayList<ItemsItem>>()

    fun setSearchGithubUsers(query: String) { // baseurl/search/users?q={buat di sini}
        RetrofitInstance.apiInstance
            .getGithubUser(query)
            .enqueue(object : Callback<GithubUsersResponse> {
                override fun onResponse(
                    call: Call<GithubUsersResponse>,
                    response: Response<GithubUsersResponse>
                ) {
                    if (response.isSuccessful) {
                        githubUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getSearchGithubUser(): LiveData<ArrayList<ItemsItem>> {
        return githubUsers
    }
}