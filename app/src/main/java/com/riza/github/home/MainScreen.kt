package com.riza.github.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.riza.github.R
import com.riza.github.compose.AppColor
import com.riza.github.compose.AppTextStyle
import com.riza.github.compose.AppTheme
import com.riza.github.home.compose.GithubUserSection
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
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                ) {
                    GithubUserSection()
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }

}



@Preview
@Composable
fun PreviewScreen() {
    MainScreen()
}