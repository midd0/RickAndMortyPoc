package com.rickandmortypoc.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rickandmortypoc.R
import com.rickandmortypoc.data.remote.dto.CharacterDetail
import com.rickandmortypoc.domain.common.DataResponse
import com.rickandmortypoc.ui.common.TopRickAppBarBack
import com.rickandmortypoc.ui.detail.viewmodel.CharacterDetailViewModel

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    characterId: String = "1",
    navigateToBack: () -> Unit
) {

    viewModel.getCharacter(characterId)
    val uiState: DataResponse<CharacterDetail> by viewModel.uiState.collectAsStateWithLifecycle()

    DetailContent(
        character = uiState.data,
        navigateToBack = navigateToBack
    )
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier, character: CharacterDetail?, navigateToBack: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopRickAppBarBack(stringResource(id = R.string.back), navigateToBack)
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                character = character,
            )
            Text(
                text = character?.name.orEmpty(),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterHorizontally)

            )
            Characteristics(character = character)
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier, character: CharacterDetail?
) {
    Column(
        modifier = modifier.background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(image = character?.image)

    }
}

@Composable
fun Characteristics(character: CharacterDetail?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        DetailProperty(label = stringResource(id = R.string.specie), value = character?.species)
        DetailProperty(label = stringResource(id = R.string.status), value = character?.status)
        DetailProperty(label = stringResource(id = R.string.gender), value = character?.gender)
        DetailProperty(
            label = stringResource(id = R.string.location), value = character?.location?.name
        )
    }
}

@Composable
fun CharacterImage(image: String?) {
    Surface(
        color = colorResource(id = R.color.rick_color),
        modifier = Modifier
            .aspectRatio(1f)
            .padding(top = 16.dp)
            .size(180.dp), shape = CircleShape
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .size(180.dp)
                .padding(
                    top = 20.dp, bottom = 15.dp, start = 50.dp, end = 50.dp
                ),
            model = image,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.description_image_character),
        )
    }
}

@Composable
fun DetailProperty(
    modifier: Modifier = Modifier,
    label: String,
    value: String?,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 4.dp),
        elevation = 3.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 4.dp),
        ) {

            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = value.orEmpty(),
                color = Color.Black,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )
        }
    }
}


