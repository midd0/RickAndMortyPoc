package com.rickandmortypoc.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rickandmortypoc.R
import com.rickandmortypoc.data.remote.dto.CharacterList
import com.rickandmortypoc.domain.common.DataResponse
import com.rickandmortypoc.domain.common.UiStatus
import com.rickandmortypoc.ui.common.TopRickAppBar
import com.rickandmortypoc.ui.common.showDialogCircularProgressBar
import com.rickandmortypoc.ui.common.showError
import com.rickandmortypoc.ui.list.viewmodel.CharactersListViewModel

@Composable
fun CharactersListScreen(
    viewModel: CharactersListViewModel = hiltViewModel(),
    navigateToCharacterDetail: (String) -> Unit
) {
    viewModel.getAllCharacters()
    val uiState: DataResponse<CharacterList> by viewModel.uiState.collectAsStateWithLifecycle()

    Column() {
        TopRickAppBar()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_header),
                contentDescription = stringResource(id = R.string.description_header),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 12.dp, end = 12.dp)
                    .align(CenterHorizontally)

            )
            CharacterListCards(uiState, navigateToCharacterDetail)
        }
    }

}


@Composable
private fun CharacterListCards(
    uiState: DataResponse<CharacterList>, navigateToCharacterDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 16.dp, start = 16.dp, end = 16.dp
            )
    ) {
        uiState.let { uiState ->
            when (uiState.responseType) {
                UiStatus.ERROR -> {
                    uiState.error?.let {
                        showError(message = it)
                    }
                }

                UiStatus.LOADING -> {
                    showDialogCircularProgressBar()
                }

                UiStatus.SUCCESS -> {
                    LazyColumn() {
                        val list = uiState.data?.results
                        list?.let { characterList ->
                            items(characterList) { character ->
                                if (character.name.isNotEmpty() || character.image.isNotEmpty()) {
                                    CharactersListRowView(character) {
                                        navigateToCharacterDetail(
                                            character.id.toString()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}







