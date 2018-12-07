package com.example.yamidpico.redditdemo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap

import kotlinx.android.synthetic.main.content_post.*

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setupPost()
    }

    private fun setupPost() {
        val post = Gson().fromJson(intent.getStringExtra("post"), HashMap::class.java)
        val data = post["data"] as? LinkedTreeMap<String, Any> ?: return
        val comments = data["num_comments"] as Double

        postTitle.text = data["title"].toString()
        subredditLink.text = data["subreddit_name_prefixed"].toString()
        author.text = "u/${data["author"]}"
        commentsText.text = "${comments.toInt()} Comments"

        Log.e("tales5", "${data["url"]}")
        Log.e("tales5", "${data["post_hint"]}")
        //TODO: agregar otros tipos de media
        Glide.with(this).load(data["url"]).into(mediaImg)
        if (data["post_hint"] == "image") {

        }
    }

}
