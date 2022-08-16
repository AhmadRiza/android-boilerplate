package com.riza.github.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by ahmadriza on 16/08/22.
 * Copyright (c) 2022 Kitabisa. All rights reserved.
 */

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    ErrorSection(message = "No internet")
}


@Composable
fun ErrorSection(modifier: Modifier = Modifier, message: String, onRetry: (() -> Unit)? = null) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp,
                vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = AppTextStyle.TextRegular.copy(color = AppColor.Neutral700)
        )
        if (onRetry != null) {
            Text(
                "Retry",
                style = AppTextStyle.TextBold.copy(
                    color = Color(0xFF2196F3),
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .clickable(onClick = onRetry)
            )
        }

    }
}