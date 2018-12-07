package com.example.yamidpico.redditdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.yamidpico.redditdemo.adapter.HotPostAdapter
import com.example.yamidpico.redditdemo.manager.PostManager
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HotPostAdapter.OnPostClickListener {

    private var disposable: CompositeDisposable? = null
    private val manager by lazy { PostManager() }
    private val adapter = HotPostAdapter(this)

    init {
        disposable = CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postList.adapter = adapter

        disposable?.add(manager.getHotPost().subscribe {
            it?.data?.children?.let {posts ->
                adapter.updatePosts(posts)
            }
        })
    }

    override fun onStop() {
        super.onStop()

        disposable?.dispose()
        disposable = null
    }

    override fun onPostClick(post: HashMap<String, Any>) {
        startActivity(
            Intent(this, PostActivity::class.java).putExtra(
                "post", Gson().toJson(post)
            )
        )
    }
}