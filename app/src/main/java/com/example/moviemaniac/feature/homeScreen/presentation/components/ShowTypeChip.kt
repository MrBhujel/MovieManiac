package com.example.moviemaniac.feature.homeScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviemaniac.feature.homeScreen.presentation.ShowType

@Composable
fun ShowTypeChip(
    selected: ShowType,
    onSelectedChange: (ShowType) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            selected = selected == ShowType.MOVIE,
            onClick = { onSelectedChange(ShowType.MOVIE) },
            label = { Text("Movies") }
        )

        FilterChip(
            selected = selected == ShowType.TV_SHOW,
            onClick = { onSelectedChange(ShowType.TV_SHOW) },
            label = { Text("Tv Shows") }
        )
    }

}