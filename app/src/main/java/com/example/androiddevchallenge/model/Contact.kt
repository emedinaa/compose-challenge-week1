package com.example.androiddevchallenge.model

data class Contact(
    val name: String, val desc: String,
    val email: String, val phone: String, val photo: Int
) {
    constructor() : this("", "", "", "", 0)
}