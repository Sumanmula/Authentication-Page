package com.example.firstapplication.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import com.example.firstapplication.model.Contact
import com.example.firstapplication.model.Person

@Composable
fun RecycleViewItem(
    item: Person,
    onImageClick: () -> Unit,
    onNameClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            .padding(1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.Image),
                contentDescription = item.Name,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(84.dp)
                    .background(Color.LightGray)
                    .clickable { onImageClick() }
            )

            Column {
                Text(
                    text = item.Name,
                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable { onNameClick() }
                )
                Text(
                    text = item.Bio,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecycleViewItemPreview() {
    val samplePerson = Person(
        Name = "Suman Mula",
        Bio = "Batsman / WicketKeeper",
        Description = "Right-hand batsman and wicketkeeper",
        Image = "https://github.com/Sumanmula/Picture/blob/main/IMG_1359-removebg-preview.png?raw=true",
        Contact = Contact(Phone = "8436963080", Email = "sumanmula2000@gmail.com"),
        Address = "Bangalore, Karnataka",
        id = "1"
    )

    RecycleViewItem(
        item = samplePerson,
        onImageClick = {},
        onNameClick = {}
    )
}