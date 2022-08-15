package com.riza.github.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
fun SearchBar(
    text : String,
    onTextChanged: (String) -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 20.dp,
            end = 20.dp,
            top = 15.dp,
            bottom = 8.dp
        )
        .background(
            color = AppColor.Neutral50,
            shape = RoundedCornerShape(10.dp)
        )
        .padding(
            horizontal = 16.dp,
            vertical = 10.dp
        )
    ) {

        val (iconSearch, iconShadow, textField, hint) = createRefs()

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = AppColor.Neutral700,
            modifier = Modifier
                .size(24.dp)
                .alpha(0.2f)
                .blur(4.dp)
                .constrainAs(iconShadow) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, 8.dp)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = AppColor.Neutral700,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(iconSearch) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
        )
        BasicTextField(
            value = text,
            onValueChange = onTextChanged,
            textStyle = AppTextStyle.TextMedium,
            modifier = Modifier.constrainAs(textField) {
                start.linkTo(iconSearch.end, 12.dp)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            }
        )
        if(text.isEmpty()) {
            Text(
                text = "Search some magician...",
                style = AppTextStyle.TextMedium.copy(color = AppColor.Neutral700),
                modifier = Modifier.constrainAs(hint) {
                    start.linkTo(iconSearch.end, 12.dp)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
            )
        }


    }
}

