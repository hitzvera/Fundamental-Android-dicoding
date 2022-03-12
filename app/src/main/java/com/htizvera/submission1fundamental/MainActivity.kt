package com.htizvera.submission1fundamental
import androidx.appcompat.widget.SearchView
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.htizvera.submission1fundamental.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<GithubUser>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGithubUser.setHasFixedSize(true)

        list.addAll(listGithubUsers)
        showRecyclerList()
    }
    private val listGithubUsers: ArrayList<GithubUser>
        @SuppressLint("Recycle")
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)
            val dataProfession = resources.getStringArray(R.array.profesi)
            val dataFollower = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val listGithubUser = ArrayList<GithubUser>()
            for(i in dataUsername.indices) {
                val user = GithubUser(
                    dataUsername[i],
                    dataName[i],
                    dataPhoto.getResourceId(i, -1),
                    dataProfession[i],
                    dataFollower[i],
                    dataFollowing[i],
                    dataCompany[i],
                    dataLocation[i],
                    dataRepository[i]
                )
                listGithubUser.add(user)
            }
            return listGithubUser
        }
    private fun showRecyclerList() {
        binding.rvGithubUser.layoutManager = LinearLayoutManager(this)
        val listGithubUserAdapter = ListGithubUserAdapter(list)
        binding.rvGithubUser.adapter = listGithubUserAdapter

        listGithubUserAdapter.setOnClickCallback(object: ListGithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUser) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: GithubUser) {
        Toast.makeText(this, "You Choose " + user.name, Toast.LENGTH_SHORT).show()

        val toDetail = Intent(this, DetailActivity::class.java)
        toDetail.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(toDetail)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.option_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.search {
//                Toast.makeText(this, "test")
//            }
//            else {
//
//            }
//        }
//    }
}