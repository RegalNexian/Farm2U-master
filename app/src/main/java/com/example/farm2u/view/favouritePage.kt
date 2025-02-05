package com.example.farm2u.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farm2u.R

// Sample Farmer Data Model
data class Farmer(val name: String, val imageRes: Int, var isFavorite: Boolean)

// Sample Farmer List
val sampleFarmers = listOf(
    Farmer("John Doe", R.drawable.farmer, true),
    Farmer("Emma Smith", R.drawable.farmer, false),
    Farmer("Liam Brown", R.drawable.farmer, true),
    Farmer("Olivia Johnson", R.drawable.farmer, false),
    Farmer("William Wilson", R.drawable.farmer, true)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favourites(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 10.dp, end = 10.dp, bottom = 100.dp)
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(50.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Filtered Favorite List
            FavList(farmers = sampleFarmers.filter { it.name.contains(searchQuery, ignoreCase = true) })
        }
    }
}

// Composable to Display List of Favorite Farmers
@Composable
fun FavList(farmers: List<Farmer>) {
    LazyColumn {
        items(farmers.size) { index ->
            FavCard(farmer = farmers[index])
        }
    }
}

// Composable to Create a Farmer Card
@Composable
fun FavCard(farmer: Farmer) {
    var isFav by remember { mutableStateOf(farmer.isFavorite) }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(90.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Farmer Image
            Image(
                painter = painterResource(farmer.imageRes),
                contentDescription = "Farmer Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Farmer Name
            Text(
                text = farmer.name,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            // Favorite Button
            IconButton(onClick = { isFav = !isFav }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = if (isFav) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
