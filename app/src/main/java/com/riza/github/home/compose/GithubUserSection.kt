package com.riza.github.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.placeholder
import com.riza.github.compose.AppColor
import com.riza.github.compose.AppTextStyle
import com.riza.github.home.GithubUserItemModel

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Composable
fun GithubUserSection(
    model: GithubUserItemModel,
    onClickUserSection: (model: GithubUserItemModel) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClickUserSection(model) })
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp
            ).testTag("gh_user_section")
    ) {
        val (
            imageAvatar, textName, textUserName,
            textDescription, textAddress, textEmail
        ) = createRefs()
        val painter = rememberAsyncImagePainter(model = model.avatarUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .placeholder(
                    visible = painter.state is AsyncImagePainter.State.Loading,
                    shape = CircleShape,
                    color = AppColor.Neutral50
                )
                .constrainAs(imageAvatar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = model.name,
            style = AppTextStyle.TextBold,
            modifier = Modifier.constrainAs(textName) {
                start.linkTo(imageAvatar.end, 8.dp)
                top.linkTo(parent.top)
            }
        )
        Text(
            text = model.userName,
            style = AppTextStyle.TextRegular.copy(
                fontSize = 10.sp
            ),
            modifier = Modifier.constrainAs(textUserName) {
                start.linkTo(textName.end, 8.dp)
                centerVerticallyTo(textName)
            }
        )
        Text(
            text = model.description,
            style = AppTextStyle.TextRegular,
            modifier = Modifier.constrainAs(textDescription) {
                top.linkTo(textName.bottom, 4.dp)
                start.linkTo(textName.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = model.address,
            style = AppTextStyle.TextRegular.copy(
                fontSize = 10.sp, color = AppColor.Neutral700
            ),
            modifier = Modifier.constrainAs(textAddress) {
                start.linkTo(textDescription.start)
                top.linkTo(textDescription.bottom, 12.dp)
            }
        )
        Text(
            text = model.email,
            style = AppTextStyle.TextRegular.copy(
                fontSize = 10.sp, color = AppColor.Neutral700
            ),
            modifier = Modifier.constrainAs(textEmail) {
                top.linkTo(textAddress.top)
                start.linkTo(textAddress.end, 16.dp)
            }
        )
    }
}
