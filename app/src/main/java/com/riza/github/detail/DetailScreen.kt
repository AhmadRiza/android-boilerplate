package com.riza.github.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riza.github.R
import com.riza.github.compose.AppColor
import com.riza.github.compose.AppTextStyle
import com.riza.github.compose.AppTheme
import com.riza.github.detail.compose.DetailProfileSection
import com.riza.github.detail.compose.DetailRepoSection
import com.riza.github.detail.compose.ShimmerDetailRepoSection
import com.riza.github.home.compose.OnBottomReached

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {
    val state by viewModel.state.observeAsState(initial = DetailViewModel.State())
    AppTheme {
        Surface(color = Color.White) {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Text(
                            text = state.titleLabel,
                            style = AppTextStyle.TextBold.copy(fontSize = 14.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            viewModel.onIntentReceived(DetailViewModel.Intent.OnBackPressed)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                                contentDescription = null,
                                tint = AppColor.Neutral900
                            )
                        }
                    }
                )
                val listState = rememberLazyListState()
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(state.displayItems) { item ->
                        when (item) {
                            DetailDisplayDividerItemModel -> {
                                RepoDivider()
                            }
                            is DetailProfileItemModel -> {
                                DetailProfileSection(model = item)
                            }
                            is DetailRepoErrorItemModel -> {
                            }
                            is DetailRepoItemModel -> {
                                DetailRepoSection(model = item)
                            }
                            EmptyRepoItemModel -> {
                            }
                            EndOfListRepoItemModel -> {
                            }
                            LoadingMoreRepoItemModel -> {
                                repeat(3) {
                                    ShimmerDetailRepoSection()
                                }
                            }
                            LoadingRepoItemModel -> {
                                repeat(5) {
                                    ShimmerDetailRepoSection()
                                }
                            }
                        }
                    }
                }
                listState.OnBottomReached(buffer = 1) {
                    viewModel.onIntentReceived(DetailViewModel.Intent.OnLoadMoreRepos)
                }
            }
        }
    }
}

@Composable
fun RepoDivider() {
    Divider(
        thickness = 1.dp,
        color = AppColor.DividerColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    )
}
