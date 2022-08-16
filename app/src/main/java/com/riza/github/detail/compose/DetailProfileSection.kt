package com.riza.github.detail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.riza.github.detail.DetailProfileItemModel

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Preview(showBackground = true)
@Composable
fun PreviewSection() {
    val model = DetailProfileItemModel(
        avatarUrl = "ss",
        name = "Riza",
        userName = "@ahmadrza",
        description = "Halo gess",
        followers = "132K",
        following = "12K",
        address = "Malang",
        email = "riza@google.com"
    )
    DetailProfileSection(model = model)
}

@Composable
fun DetailProfileSection(model: DetailProfileItemModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
                bottom = 21.dp
            )
    ) {
        val (imageAvatar, textName, textUserName, textDescription,
            viewFollower, viewAddress, viewEmail) = createRefs()
        val painter = rememberAsyncImagePainter(model = model.avatarUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .constrainAs(imageAvatar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = model.name,
            style = AppTextStyle.TextBold.copy(fontSize = 20.sp),
            modifier = Modifier.constrainAs(textName) {
                top.linkTo(imageAvatar.top, 11.dp)
                start.linkTo(imageAvatar.end, 12.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = model.userName,
            style = AppTextStyle.TextRegular.copy(fontSize = 14.sp),
            modifier = Modifier.constrainAs(textUserName) {
                top.linkTo(textName.bottom, 4.dp)
                start.linkTo(textName.start)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = model.description,
            style = AppTextStyle.TextRegular.copy(fontSize = 14.sp),
            modifier = Modifier.constrainAs(textDescription) {
                top.linkTo(textUserName.bottom, 12.dp)
                start.linkTo(textName.start)
                width = Dimension.fillToConstraints
            }
        )

        Row(modifier = Modifier.constrainAs(viewFollower) {
            start.linkTo(textUserName.start)
            top.linkTo(textDescription.bottom, 12.dp)
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_followers),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = AppColor.Neutral900
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = model.followers,
                style = AppTextStyle.TextBold.copy(fontSize = 14.sp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Followers",
                style = AppTextStyle.TextRegular.copy(fontSize = 14.sp),
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(2.dp)
                    .background(AppColor.Neutral900, shape = CircleShape)
            )
            Text(
                text = model.following,
                style = AppTextStyle.TextBold.copy(fontSize = 14.sp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Following",
                style = AppTextStyle.TextRegular.copy(fontSize = 14.sp),
            )
        }

        Row(modifier = Modifier.constrainAs(viewAddress) {
            start.linkTo(textUserName.start)
            top.linkTo(viewFollower.bottom, 12.dp)
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_address),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = AppColor.Neutral900
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = model.address,
                style = AppTextStyle.TextRegular.copy(fontSize = 14.sp),
            )
        }

        Row(modifier = Modifier.constrainAs(viewEmail) {
            start.linkTo(textUserName.start)
            top.linkTo(viewAddress.bottom, 12.dp)
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = AppColor.Neutral900
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = model.email,
                style = AppTextStyle.TextRegular.copy(fontSize = 14.sp),
            )
        }
    }
}