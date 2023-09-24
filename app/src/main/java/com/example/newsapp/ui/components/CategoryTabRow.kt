package com.example.newsapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    categories: List<String>,
    onTabSelected: (Int) -> Unit
){
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = category,
                        modifier = modifier
                            .padding(vertical = 8.dp, horizontal = 2.dp )
                    )
                }
            )
        }
    }
}