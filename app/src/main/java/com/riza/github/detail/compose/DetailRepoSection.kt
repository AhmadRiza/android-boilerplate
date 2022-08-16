package com.riza.github.detail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.riza.github.detail.DetailRepoItemModel

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Preview(showBackground = true)
@Composable
fun DetailRepoPreview() {
    val model = DetailRepoItemModel(
        avatarUrl = "ss",
        name = "weather-controller",
        description = "The power of Zeus",
        starsCount = "100K",
        lastUpdate = "7 days ago"
    )
    DetailRepoSection(model = model)
}

@Composable
fun DetailRepoSection(model: DetailRepoItemModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        val (imageAvatar, textName, textDescription,
            iconStar, textStar, textLastUpdate) = createRefs()
        val painter = rememberAsyncImagePainter(model = model.avatarUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .constrainAs(imageAvatar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = model.name,
            style = AppTextStyle.TextBold.copy(fontSize = 16.sp),
            modifier = Modifier.constrainAs(textName) {
                top.linkTo(imageAvatar.top, 7.dp)
                end.linkTo(parent.end)
                start.linkTo(imageAvatar.end, 12.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = model.description,
            style = AppTextStyle.TextRegular.copy(fontSize = 14.sp, color = AppColor.Neutral700),
            modifier = Modifier.constrainAs(textDescription) {
                top.linkTo(textName.bottom, 8.dp)
                start.linkTo(textName.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(iconStar) {
                    top.linkTo(textDescription.bottom, 12.dp)
                    start.linkTo(textName.start)
                },
            tint = AppColor.Neutral900
        )
        Text(
            text = model.starsCount,
            style = AppTextStyle.TextRegular.copy(fontSize = 10.sp, color = AppColor.Neutral700),
            modifier = Modifier.constrainAs(textStar) {
                top.linkTo(iconStar.top)
                bottom.linkTo(iconStar.bottom)
                start.linkTo(iconStar.end, 2.dp)
            }
        )
        Text(
            text = model.lastUpdate,
            style = AppTextStyle.TextRegular.copy(fontSize = 10.sp, color = AppColor.Neutral700),
            modifier = Modifier.constrainAs(textLastUpdate) {
                top.linkTo(iconStar.top)
                bottom.linkTo(iconStar.bottom)
                start.linkTo(textStar.end, 16.dp)
            }
        )
    }
}