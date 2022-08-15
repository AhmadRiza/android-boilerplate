package com.riza.github.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.riza.github.R
import com.riza.github.compose.AppColor
import com.riza.github.compose.AppTextStyle

/**
 * Created by ahmadriza on 15/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */



@Composable
fun GithubUserSection() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp
            )
    ) {
        val (imageAvatar, textName, textUserName,
            textDescription, textAddress, textEmail) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.avat),
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
            text = "Riza",
            style = AppTextStyle.TextBold,
            modifier = Modifier.constrainAs(textName) {
                start.linkTo(imageAvatar.end, 8.dp)
                top.linkTo(parent.top)
            }
        )

        Text(
            text = "@riza",
            style = AppTextStyle.TextRegular.copy(
                fontSize = 10.sp
            ),
            modifier = Modifier.constrainAs(textUserName) {
                start.linkTo(textName.end, 8.dp)
                centerVerticallyTo(textName)
            }
        )

        Text(
            text = "Developer of Google",
            style = AppTextStyle.TextRegular,
            modifier = Modifier.constrainAs(textDescription) {
                top.linkTo(textName.bottom, 4.dp)
                start.linkTo(textName.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = "malang, jatim",
            style = AppTextStyle.TextRegular.copy(
                fontSize = 10.sp, color = AppColor.Neutral700
            ),
            modifier = Modifier.constrainAs(textAddress) {
                start.linkTo(textDescription.start)
                top.linkTo(textDescription.bottom, 12.dp)
            }
        )

        Text(
            text = "a@riza.com",
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
