package com.example.firstapplication.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.firstapplication.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onPersonClick: (Int) -> Unit
) {
    val playerList by viewModel.playerList
    val searchQuery = viewModel.searchQuery.value
    val zoomedPerson = viewModel.zoomedPerson.value

    when {
        playerList == null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        playerList!!.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Failed to load players or no data available.")
            }
        }

        else -> {
            val searchedPlayers = viewModel.searchList()

            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Player List",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(16.dp)
                    )

                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.searchQuery.value = it },
                        placeholder = { Text("Search by name or character") },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.searchQuery.value = "" }) {
                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(28.dp)
                    ) {
                        itemsIndexed(searchedPlayers) { index, item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                RecycleViewItem(
                                    item = item,
                                    onImageClick = { viewModel.onImageClick(item) },
                                    onNameClick = { onPersonClick(index) }
                                )
                            }
                        }
                    }
                }

                zoomedPerson?.let { person ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x80000000))
                            .clickable { viewModel.clearOverlay() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(person.Image),
                            contentDescription = person.Name,
                            modifier = Modifier
                                .size(320.dp)
                                .clip(CircleShape)
                                .border(4.dp, Color.White, CircleShape)
                                .background(Color.White)
                        )
                    }
                }
            }
        }
    }
}