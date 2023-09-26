package com.example.newsapp.ui.news_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.domain.modal.Article
import com.example.newsapp.R
//import com.example.newsapp.ui.components.ImageHolder

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    article: Article,
    onReadFullStoryButtonClicked: () -> Unit
){
    Surface(
        modifier = modifier.padding(16.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(8.dp))
//            ImageHolder(imageUrl = article.urlToImage)
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build()
                ,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                placeholder = painterResource(id = R.drawable.loading),
                error = painterResource(id = R.drawable.news)
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = article.content ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(8.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = article.author ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = modifier.height(8.dp))
            Button(
                onClick = onReadFullStoryButtonClicked,
                modifier = modifier.fillMaxWidth()
            ){
                Text(text = stringResource(id = R.string.read_full_article))
            }
            Spacer(modifier = modifier.height(20.dp))
        }
    }
}