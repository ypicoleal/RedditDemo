package com.example.yamidpico.redditdemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yamidpico.redditdemo.R

class HotPostAdapter : RecyclerView.Adapter<HotPostAdapter.ViewHolder>(){

    private var posts: List<HashMap<String, Any>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    fun updatePosts(posts: List<HashMap<String, Any>>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun getItemCount() = posts?.size ?: 0

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}