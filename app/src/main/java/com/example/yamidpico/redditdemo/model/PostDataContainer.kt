package com.example.yamidpico.redditdemo.model

data class PostDataContainer(
    val after: String? = null,
    val before: String? = null,
    val children: List<HashMap<String, Any>>,
    val dist: Int = 0,
    val modhash: String = "")