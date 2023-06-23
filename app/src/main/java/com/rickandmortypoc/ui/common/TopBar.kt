package com.rickandmortypoc.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.rickandmortypoc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopRickAppBar(headerText: String = "") {
    TopAppBar(
        title = {
            Text(headerText)
        },

        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.rick_color))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopRickAppBarBack(backText: String = "", navigateToBack: (() -> Unit)) {
    TopAppBar(
        title = {
            Text(backText)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.rick_color)),
        navigationIcon = {
            IconButton(onClick = { navigateToBack.invoke() }) {
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.back))
            }
        })
}
