package com.rickandmortypoc.ui

import android.os.Handler
import android.os.Looper
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rickandmortypoc.R

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 4f,
            tween(durationMillis = 500, easing = {
                OvershootInterpolator(1f).getInterpolation(it)

            })
        )
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToHome()
        }, 3000L)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color.Green,
                        Color.White
                    )
                )
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.rick_splash),
            contentDescription = stringResource(id = R.string.description_logo),
            Modifier.scale(scale.value)
        )
    }
}
