package com.example.farm2u.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farm2u.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile2(navController: NavController) {
    var farmerName by remember { mutableStateOf("") }
    var farmerPhone by remember { mutableStateOf("") }
    var farmerLocation by remember { mutableStateOf("") }
    var farmerCrops by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Gray, shape = CircleShape)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(R.drawable.farmer),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize()
                )
            }

            TextField(
                value = farmerName,
                onValueChange = { farmerName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = farmerPhone,
                onValueChange = { farmerPhone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = farmerLocation,
                onValueChange = { farmerLocation = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = farmerCrops,
                onValueChange = { farmerCrops = it },
                label = { Text("Preferred Crops") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
