package com.rickandmortypoc.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rickandmortypoc.R
import com.rickandmortypoc.data.remote.dto.CharacterDetail

@Composable
fun CharactersListRowView(
    character: CharacterDetail,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick() }),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .padding(top = 8.dp, bottom = 8.dp)
        )

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {

            Text(
                character.name, style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                String.format(stringResource(id = R.string.episodes), character.episode.size),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    Divider()
}


