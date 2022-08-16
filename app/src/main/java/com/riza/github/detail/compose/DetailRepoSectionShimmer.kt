package com.riza.github.detail.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.riza.github.compose.AppColor

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Preview(showBackground = true)
@Composable
fun DetailRepoShimmerPreview() {
    ShimmerDetailRepoSection()
}
@Composable
fun ShimmerDetailRepoSection() {
    val shimmerModifier = Modifier.placeholder(
        visible = true,
        color = AppColor.Neutral50,
        shape = RoundedCornerShape(4.dp),
        highlight = PlaceholderHighlight.shimmer(
            Color.White,
            PlaceholderDefaults.shimmerAnimationSpec
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .size(32.dp)
                .placeholder(
                    visible = true,
                    color = AppColor.Neutral50,
                    shape = CircleShape,
                    highlight = PlaceholderHighlight.shimmer(
                        Color.White,
                        PlaceholderDefaults.shimmerAnimationSpec
                    )
                )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Spacer(
                modifier = shimmerModifier
                    .width(100.dp)
                    .height(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = shimmerModifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Spacer(
                modifier = shimmerModifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Spacer(
                modifier = shimmerModifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(
                modifier = shimmerModifier
                    .width(100.dp)
                    .height(5.dp)
            )
        }
    }
}
