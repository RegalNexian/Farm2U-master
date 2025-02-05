package com.example.farm2u.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farm2u.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavController) {
    var buyerName by remember { mutableStateOf("") }
    var buyerPhone by remember { mutableStateOf("") }
    var buyerLocation by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle dropdown menu click */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
                        )
                    }
                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { /* Handle dismiss */ }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Language Setting") },
                            onClick = { /* Handle Language Setting */ }
                        )
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = { /* Handle Logout */ }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // Profile Picture Section
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Gray, shape = CircleShape)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.farmer), // Placeholder image
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name Field
            TextField(
                value = buyerName,
                onValueChange = { buyerName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Phone Number Field
            TextField(
                value = buyerPhone,
                onValueChange = { buyerPhone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Location Field
            TextField(
                value = buyerLocation,
                onValueChange = { buyerLocation = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    // Handle save action here
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Save", fontWeight = FontWeight.Bold)
            }
        }
    }
}
