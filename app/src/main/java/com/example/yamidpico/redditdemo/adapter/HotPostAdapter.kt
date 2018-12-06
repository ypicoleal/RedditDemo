package com.example.yamidpico.redditdemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yamidpico.redditdemo.R
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.item_post.view.*

class HotPostAdapter : RecyclerView.Adapter<HotPostAdapter.ViewHolder>(){

    private var posts: List<HashMap<String, Any>> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    fun updatePosts(posts: List<HashMap<String, Any>>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val post = posts[position]["data"] as LinkedTreeMap<String, Any>
        val comments = post["num_comments"] as Double
        viewHolder.itemView.apply {
            postTitle.text = post["title"].toString()
            subredditLink.text = post["subreddit_name_prefixed"].toString()
            author.text = "u/${post["author"]}"
            commentsText.text = "${comments.toInt()} Comments"
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}