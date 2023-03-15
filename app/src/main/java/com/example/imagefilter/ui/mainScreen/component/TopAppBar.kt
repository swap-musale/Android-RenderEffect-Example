package com.example.imagefilter.ui.mainScreen.component

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.imagefilter.R

@Composable
fun AppToolBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
    )
}

@Preview
@Composable
fun AppToolBarPreview() {
    AppToolBar()
}
