package com.example.androiddevchallenge.model

data class Pet(
    val id: Int, val name: String,
    val desc: String, val color: Long,
    val about: String, val owner: Contact, val photos: List<Int>
) {
    constructor() : this(0, "", "", 0xff000000, "", Contact(), emptyList())
}