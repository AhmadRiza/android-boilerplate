package com.riza.github.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.riza.github.compose.AppTheme
import com.riza.github.home.compose.SearchBar

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Composable
fun MainScreen() {

    AppTheme {
        Surface(color = Color.White) {
            Column(Modifier.fillMaxWidth()) {
                SearchBar(text = "", onTextChanged = {})
            }
        }

    }

}


@Composable
fun GithubUserSection() {

}



@Preview
@Composable
fun PreviewScreen() {
    MainScreen()
}