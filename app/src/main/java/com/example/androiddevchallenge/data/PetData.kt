package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.model.Contact
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.R

object PetData {
    private const val about =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
    private val contact =
        Contact("Eduardo Medina", "Dog's owner", "abc@abc.com", "123456", R.drawable.edu)

    val petList = mutableListOf(
        Pet(
            1,
            "Firulais",
            "Dog 2 desc",
            0xffEDB506,
            about,
            contact,
            listOf(R.drawable.puppy02, R.drawable.puppy02, R.drawable.puppy02, R.drawable.puppy02)
        ),
        Pet(
            2,
            "Bobby",
            "Dog 3 desc",
            0xff589CC0,
            about,
            contact,
            listOf(R.drawable.puppy03, R.drawable.puppy03, R.drawable.puppy03, R.drawable.puppy03)
        ),
        Pet(
            3,
            "Winnie",
            "Dog 3 desc",
            0xffE07031,
            about,
            contact,
            listOf(R.drawable.puppy04, R.drawable.puppy04, R.drawable.puppy04, R.drawable.puppy04)
        ),
        Pet(
            4,
            "Pappu",
            "Dog 4 desc",
            0xffEDB506,
            about,
            contact,
            listOf(R.drawable.puppy05, R.drawable.puppy05, R.drawable.puppy05, R.drawable.puppy05)
        ),
        Pet(
            5,
            "Ares",
            "Dog desc",
            0xff589CC0,
            about,
            contact,
            listOf(R.drawable.puppy01, R.drawable.puppy01, R.drawable.puppy01, R.drawable.puppy01)
        ),
    ).toList()
}