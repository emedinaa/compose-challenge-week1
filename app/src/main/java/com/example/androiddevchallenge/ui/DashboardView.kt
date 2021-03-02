package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.data.PetData
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.R

@Composable
fun DashboardView(navController: NavController, petList: List<Pet>) {
    Surface(color = MaterialTheme.colors.background) {
        View(navController, petList)
    }
}

@Composable
fun View(navController: NavController, petList: List<Pet>) {
    Column {
        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(Modifier.size(40.dp))
            Header()
            Spacer(Modifier.size(10.dp))
            Title()
            Spacer(Modifier.size(40.dp))
            Search()
            Spacer(Modifier.size(30.dp))
        }
        Filter()
        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(Modifier.size(30.dp))
            Text(text = "Adopt pet", style = MaterialTheme.typography.h6)
        }
        Spacer(Modifier.size(10.dp))
        Container(navController, petList)
    }
}

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.ic_notes_24px),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.edu), contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .border(BorderStroke(1.dp, Color(0xff01B4D2)), CircleShape)
        )
    }
}

@Composable
fun Title() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Hello Human", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.size(4.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_face_24px), contentDescription = "",
            colorFilter = ColorFilter.tint(Color(0xffEDB506))
        )
    }
}

@Composable
fun SearchBox() {
    Box(
        modifier = Modifier
            .size(40.dp, 40.dp)
            .background(Color(0xff01B4D2), RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(painterResource(R.drawable.ic_search_24px), "")
    }
}

@Composable
fun Search() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        val textValue = remember { mutableStateOf("") }
        TextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            trailingIcon = { SearchBox() },
        )
    }
}

@Composable
fun Filter() {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.size(8.dp))
            FilterButton()
            Spacer(modifier = Modifier.size(8.dp))
            FilterItem("Cat")
            Spacer(modifier = Modifier.size(8.dp))
            FilterItem("Dog", true)
            Spacer(modifier = Modifier.size(8.dp))
            FilterItem("Birds")
            Spacer(modifier = Modifier.size(8.dp))
            FilterItem("Rabbits")
            Spacer(modifier = Modifier.size(8.dp))
            FilterItem("Fish")
            Spacer(modifier = Modifier.size(8.dp))
            FilterItem("Other")
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun FilterButton() {
    Box(
        modifier = Modifier
            .size(50.dp, 42.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, Color(0xffF2FBFD)), RoundedCornerShape(8.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painterResource(R.drawable.ic_filter_list_24px), "")
    }
}

@Composable
fun FilterItem(text: String, selected: Boolean = false) {
    val bgColor = if (selected) Color(0xff01B4D2) else Color(0xffF2FBFD)
    val textColor = if (selected) Color(0xffffffff) else Color(0xff000000)
    Box(
        modifier = Modifier
            .height(42.dp)
            .background(bgColor, RoundedCornerShape(8.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor)
    }
}

@Composable
fun Container(navController: NavController, petList: List<Pet>) {
    val scrollState = rememberScrollState()
    LazyRow(
        modifier = Modifier
            .scrollable(scrollState, Orientation.Horizontal)
    ) {
        items(petList) {
            Card(navController, scrollState.value, it)
        }
    }
}

@Composable
fun Card(navController: NavController, scrollValue: Int, pet: Pet) {
    Log.v("CONSOLE", "$scrollValue")
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .size(200.dp, 300.dp)
            .padding(8.dp)
            .background(Color(pet.color), RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("detail/${pet.id}")
            }) {
        Image(
            painter = painterResource(pet.photos[0]), "",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 0.dp),
            //.absoluteOffset(y = -(scrollValue * 0.1f).dp),
            contentScale = ContentScale.FillHeight,
        )
    }
}

@Preview
@Composable
fun DashboardPreview() {
    MyTheme {
        View(rememberNavController(), PetData.petList)
    }
}

@Preview
@Composable
fun CardPreview() {
    Card(rememberNavController(), 0, PetData.petList.first())
}
