package com.example.yamidpico.redditdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.yamidpico.redditdemo.manager.PostManager
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    var disposable: CompositeDisposable? = null
    private val manager by lazy { PostManager() }

    init {
        disposable = CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable?.add(manager.getHotPost().subscribe {
            Log.e("tales5", "posts: ${it?.data?.children}")
        })
    }

    override fun onStop() {
        super.onStop()

        disposable?.dispose()
        disposable = null
    }

}
