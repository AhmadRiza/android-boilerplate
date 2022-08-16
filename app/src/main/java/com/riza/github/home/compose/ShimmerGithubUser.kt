package com.riza.github.home.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.riza.github.compose.AppColor

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */
@Preview
@Composable
fun ShimmerGithubUser() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp
            )
    ) {
        val (
            imageAvatar, textName,
            textDescription, textAddress
        ) = createRefs()
        val shimmerModifier = Modifier.placeholder(
            visible = true,
            color = AppColor.Neutral50,
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(
                Color.White,
                PlaceholderDefaults.shimmerAnimationSpec
            )
        )

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
                .constrainAs(imageAvatar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Spacer(
            modifier = shimmerModifier.height(20.dp).width(100.dp)
                .constrainAs(textName) {
                    start.linkTo(imageAvatar.end, 8.dp)
                    top.linkTo(parent.top)
                }
        )

        Spacer(

            modifier = shimmerModifier.height(20.dp).width(200.dp).constrainAs(textDescription) {
                top.linkTo(textName.bottom, 4.dp)
                start.linkTo(textName.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Spacer(

            modifier = shimmerModifier.height(10.dp).width(40.dp).constrainAs(textAddress) {
                start.linkTo(textDescription.start)
                top.linkTo(textDescription.bottom, 12.dp)
            }
        )
    }
}
