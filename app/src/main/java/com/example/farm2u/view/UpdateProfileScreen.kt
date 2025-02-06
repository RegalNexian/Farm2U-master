package com.example.farm2u.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.farm2u.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileScreen(navController: NavController) {
    var farmerName by remember { mutableStateOf("John Doe") }
    var farmerPhone by remember { mutableStateOf("+91 9876543210") }
    var farmerLocation by remember { mutableStateOf("Pune, India") }
    var farmerCrops by remember { mutableStateOf("Wheat, Rice") }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> profileImageUri = uri }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Update Profile", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = profileImageUri?.let { rememberAsyncImagePainter(it) }
                        ?: painterResource(R.drawable.farmer),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Gray, CircleShape)
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { imagePickerLauncher.launch("image/*") },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Photo", tint = Color.White)
                }
            }

            OutlinedTextField(
                value = farmerName,
                onValueChange = { farmerName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = farmerPhone,
                onValueChange = { farmerPhone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = farmerLocation,
                onValueChange = { farmerLocation = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = farmerCrops,
                onValueChange = { farmerCrops = it },
                label = { Text("Preferred Crops") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Save Changes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
