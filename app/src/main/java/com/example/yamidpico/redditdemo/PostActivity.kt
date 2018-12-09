package com.example.yamidpico.redditdemo

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.MediaController
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

        //TODO: agregar otros tipos de media
        when (data["post_hint"]) {
            "image" -> {
                mediaImg.visibility = View.VISIBLE
                Glide.with(this).load(data["url"]).into(mediaImg)
            }
            "hosted:video" -> {
                val url =
                    ((data["media"] as LinkedTreeMap<*, *>)["reddit_video"] as LinkedTreeMap<*, *>)["scrubber_media_url"].toString()
                val controller = MediaController(this)
                controller.setAnchorView(videoView)
                videoView.apply {
                    visibility = View.VISIBLE
                    setMediaController(controller)
                    setVideoURI(Uri.parse(url))
                    start()
                }
            }
            "rich:video" -> {
                val escapedHtml =
                    ((data["media"] as LinkedTreeMap<*, *>)["oembed"] as LinkedTreeMap<*, *>)["html"].toString()
                val html = if (Build.VERSION.SDK_INT >= 24) {
                    Html.fromHtml(escapedHtml, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    Html.fromHtml(escapedHtml)
                }
                embedView.apply {
                    visibility = View.VISIBLE
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    setInitialScale(30)
                    loadData(html.toString(), "text/html; charset=utf-8", "UTF-8")
                }
            }
        }
    }

}
