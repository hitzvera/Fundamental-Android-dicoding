package com.hitzvera.secondsubmission.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hitzvera.secondsubmission.api.RetrofitInstance
import com.hitzvera.secondsubmission.data.model.DetailUserResponse
import com.hitzvera.secondsubmission.data.model.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<ItemsItem>>()

    fun setListFollowers(username: String) {
        RetrofitInstance.apiInstance
            .getGithubUserFollowers(username)
            .enqueue(object : Callback<ArrayList<ItemsItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemsItem>>,
                    response: Response<ArrayList<ItemsItem>>
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                    Log.d("Failure ", t.message.toString())
                }
            })
    }

    fun getListFollowers(): LiveData<ArrayList<ItemsItem>> = listFollowers

}