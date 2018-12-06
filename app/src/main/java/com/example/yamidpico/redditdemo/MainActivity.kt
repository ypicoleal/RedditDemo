package com.example.yamidpico.redditdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.yamidpico.redditdemo.adapter.HotPostAdapter
import com.example.yamidpico.redditdemo.manager.PostManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: CompositeDisposable? = null
    private val manager by lazy { PostManager() }
    private val adapter = HotPostAdapter()

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

}