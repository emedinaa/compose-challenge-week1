/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PetData
import com.example.androiddevchallenge.model.Contact
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun DetailView(navController: NavController, petId: Int) {
    val pet = PetData.petList.find { (it.id == petId) }
    Surface(color = MaterialTheme.colors.background) {
        View(navController, pet ?: Pet())
    }
}

@Composable
fun View(navController: NavController, pet: Pet) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Header(navController)
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PetCard(pet)
                    Spacer(Modifier.size(20.dp))
                    ContactView(pet.owner)
                    Spacer(Modifier.size(10.dp))
                    AboutSection(pet.about)
                    Spacer(Modifier.size(10.dp))
                    PictureAndVideoSection(pet.color, pet.photos)
                }
            }
            item {
                Bottom()
            }
        }
    }
}

@Composable
fun Header(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Image(
            painterResource(id = R.drawable.ic_keyboard_backspace_24px),
            contentDescription = "back",
            modifier = Modifier
                .width(40.dp)
                .clickable {
                    navController.popBackStack()
                },
        )
        Text(
            text = "Pet Adoption",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PetCard(pet: Pet) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .background(
                    Color(pet.color), RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Image(
                painter = painterResource(pet.photos.first()), "pet",
                modifier = Modifier
                    .height(150.dp)
                    .wrapContentWidth()
                    .padding(10.dp, 10.dp, 10.dp, 0.dp),
                contentScale = ContentScale.FillHeight,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(text = pet.name, style = MaterialTheme.typography.h6)
                Text(text = pet.desc, style = MaterialTheme.typography.body1)
            }
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(Color(0xFFE6F7FB), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_favorite_24px),
                    contentDescription = "favorite",
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun ContactView(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(
                    id =
                    R.drawable.edu
                ),
                contentDescription = "contact",
                Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Column(modifier = Modifier.padding(8.dp, 0.dp)) {
                Text(text = contact.name, style = MaterialTheme.typography.h6)
                Text(text = contact.desc, style = MaterialTheme.typography.body1)
            }
        }

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(Color(0xFFE6F7FB), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_email_24px),
                    contentDescription = "email",
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(Color(0xFFE6F7FB), CircleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone_24px),
                    contentDescription = "phone",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun AboutSection(about: String) {
    Column {
        Text(text = "About", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = about)
    }
}

@Composable
fun PictureAndVideoSection(color: Long, petPhotoList: List<Int>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Picture & Video", style = MaterialTheme.typography.h6)
        Spacer(Modifier.size(8.dp))
        LazyRow(horizontalArrangement = Arrangement.SpaceEvenly) {
            items(petPhotoList) {
                SmallCard(color, it)
            }
        }
    }
}

@Composable
fun SmallCard(color: Long, image: Int) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(200.dp, 200.dp)
            .padding(8.dp)
            .background(Color(color), RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(image), "",
            modifier = Modifier
                .height(150.dp)
                .wrapContentWidth()
                .padding(10.dp, 10.dp, 10.dp, 0.dp),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun Bottom() {
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff01B4D2)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Adopt me", color = Color.White)
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
    MyTheme {
        View(rememberNavController(), PetData.petList.first())
    }
}

@Preview
@Composable
fun AboutPreview() {
    MyTheme {
        AboutSection("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
    }
}

@Preview
@Composable
fun ContactPreview() {
    MyTheme {
        ContactView(
            Contact(
                "Eduardo Medina", "desc", "abc@gmail.com", "123456",
                R.drawable.edu
            )
        )
    }
}
