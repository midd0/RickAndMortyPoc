package com.rickandmortypoc.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmortypoc.data.IoDispatcher
import com.rickandmortypoc.data.remote.dto.CharacterDetail
import com.rickandmortypoc.domain.common.BaseResult
import com.rickandmortypoc.domain.common.DataResponse
import com.rickandmortypoc.domain.common.UiStatus
import com.rickandmortypoc.domain.usecase.getCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: getCharacterUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private var mutableDetail =
        MutableStateFlow<DataResponse<CharacterDetail>>(DataResponse(responseType = UiStatus.LOADING))
    val uiState: StateFlow<DataResponse<CharacterDetail>>
        get() {
            return mutableDetail
        }

    fun getCharacter(id: String) = viewModelScope.launch {
        when (val result = withContext(dispatcher) { getCharacterUseCase.invoke(id) }) {
            is BaseResult.Error -> {
                mutableDetail.value =
                    DataResponse(responseType = UiStatus.ERROR, error = result.exception)
            }

            is BaseResult.Success -> {
                mutableDetail.value =
                    DataResponse(responseType = UiStatus.SUCCESS, data = result.data)
            }
        }
    }
}