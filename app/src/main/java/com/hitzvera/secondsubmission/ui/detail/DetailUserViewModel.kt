package com.hitzvera.secondsubmission.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitzvera.secondsubmission.api.RetrofitInstance
import com.hitzvera.secondsubmission.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val githubUser = MutableLiveData<DetailUserResponse>()

    fun setGithubUserDetail(username: String) { // param menyesuaikan endpoint api
        RetrofitInstance.apiInstance
            .getDetailGithubUser(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        githubUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure ", t.message.toString())
                }
            })
    }

    fun getGithubUserDetail(): LiveData<DetailUserResponse> {
        return githubUser
    }
}