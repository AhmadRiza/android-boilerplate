package com.riza.github.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.placeholder
import com.riza.github.R
import com.riza.github.compose.AppColor
import com.riza.github.compose.AppTextStyle
import com.riza.github.compose.AppTheme
import com.riza.github.compose.ErrorSection
import com.riza.github.home.MainViewModel.Intent
import com.riza.github.home.MainViewModel.Intent.OnLoadMore
import com.riza.github.home.MainViewModel.Intent.OnSearchBarTextChanged
import com.riza.github.home.compose.GithubUserSection
import com.riza.github.home.compose.OnBottomReached
import com.riza.github.home.compose.SearchBar
import com.riza.github.home.compose.ShimmerGithubUser

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val state by viewModel.state.observeAsState(initial = MainViewModel.State())
    AppTheme {
        Surface(color = Color.White) {
            Column(Modifier.fillMaxWidth()) {
                SearchBar(text = state.query, onTextChanged = {
                    viewModel.onIntentReceived(OnSearchBarTextChanged(it))
                })
                val listState = rememberLazyListState()
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                ) {
                    items(state.displayItems) { item: MainDisplayItemModel ->
                        when(item) {
                            EmptySearchResultInfoItemModel -> {
                                ErrorSection(
                                    modifier = Modifier.height(300.dp),
                                    message = "There is no result related with \"${state.query}\""
                                )
                            }
                            EndOfUsersListItemModel -> {

                            }
                            is ErrorSearchUserItemModel -> {
                                ErrorSection(
                                    modifier = Modifier.height(300.dp),
                                    message = item.message
                                ) {
                                    viewModel.onIntentReceived(Intent.RetrySearchUser)
                                }
                            }
                            is GithubUserItemModel -> {
                                GithubUserSection(model = item, onClickUserSection = {
                                    viewModel.onIntentReceived(
                                        Intent.OnClickUser(it.id)
                                    )
                                })
                            }
                            LoadingItemModel -> {
                                repeat(5){ ShimmerGithubUser()}
                            }
                            LoadingMoreItemModel -> {
                                repeat(3){ ShimmerGithubUser()}
                            }
                            UserDividerItemModel -> {
                                UserDivider()
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                listState.OnBottomReached(buffer = 1) {
                    viewModel.onIntentReceived(OnLoadMore)
                }
            }
        }

    }

}

@Composable
fun UserDivider() {
    Divider(
        thickness = 1.dp,
        color = AppColor.DividerColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 27.dp, end = 24.dp)
    )
}