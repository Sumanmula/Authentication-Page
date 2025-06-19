package com.example.firstapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.firstapplication.model.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailsScreen(person: Person) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(person.Name) })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(person.Image),
                contentDescription = person.Name,
                modifier = Modifier.size(200.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Name: ${person.Name}")
            Text("Bio: ${person.Bio}")
            Text("Description: ${person.Description}")
            Text("Phone: ${person.Contact.Phone}")
            Text("Email: ${person.Contact.Email}")
            Text("Address: ${person.Address}")
        }
    }
}