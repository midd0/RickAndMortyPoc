package com.rickandmortypoc.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmortypoc.data.IoDispatcher
import com.rickandmortypoc.data.remote.dto.CharacterList
import com.rickandmortypoc.domain.common.BaseResult
import com.rickandmortypoc.domain.common.DataResponse
import com.rickandmortypoc.domain.common.UiStatus
import com.rickandmortypoc.domain.usecase.getAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getAllCharacterUseCase: getAllCharactersUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private var mutableStateList =
        MutableStateFlow<DataResponse<CharacterList>>(DataResponse(responseType = UiStatus.LOADING))
    val uiState: StateFlow<DataResponse<CharacterList>>
        get() {
            return mutableStateList
        }

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        when (val result = withContext(dispatcher) { getAllCharacterUseCase.invoke() }) {
            is BaseResult.Error -> {
                mutableStateList.value =
                    DataResponse(responseType = UiStatus.ERROR, error = result.exception)
            }

            is BaseResult.Success -> {
                mutableStateList.value =
                    DataResponse(responseType = UiStatus.SUCCESS, data = result.data)
            }
        }
    }
}