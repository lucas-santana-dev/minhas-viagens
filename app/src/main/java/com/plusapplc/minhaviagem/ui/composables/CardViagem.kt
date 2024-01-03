package com.plusapplc.minhaviagem.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.plusapplc.minhaviagem.R

@Composable
fun CardViagem(
    nomeDaViagen: String,
    localDaViagem: String,
    descricaoDaViagem: String?

) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(459.dp)
            .padding(16.dp)
            .clickable() { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Column(

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(331.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = nomeDaViagen,
                fontWeight = FontWeight.Black,
                color = Color.Black,
            )
            Text(text = localDaViagem,  color = Color.Black)
            descricaoDaViagem?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}