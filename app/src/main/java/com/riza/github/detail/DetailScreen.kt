package com.riza.github.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.riza.github.R
import com.riza.github.compose.AppColor
import com.riza.github.compose.AppTextStyle
import com.riza.github.compose.AppTheme
import com.riza.github.detail.compose.DetailRepoPreview
import com.riza.github.detail.compose.PreviewSection

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@Preview
@Composable
fun DetailScreen() {
    AppTheme {
        Surface(color = Color.White) {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                    Text(text = "User Detail", style = AppTextStyle.TextBold.copy(fontSize = 14.sp))
                    },
                    navigationIcon = {
                        IconButton(onClick = {  }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                                contentDescription = null,
                                tint = AppColor.Neutral900
                            )
                        }
                    }
                )
                val lazyState = rememberLazyListState()
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f), state = lazyState) {
                    items(1) {
                        PreviewSection()
                        DetailRepoPreview()
                    }
                }
            }
        }
    }

}
