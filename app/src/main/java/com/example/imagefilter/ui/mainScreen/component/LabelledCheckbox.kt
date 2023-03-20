package com.example.imagefilter.ui.mainScreen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.imagefilter.ui.theme.spacing

@Composable
fun LabelledCheckbox(
    checked: Boolean,
    checkBoxLabel: String,
    onCheckedChange: ((Boolean) -> Unit),
    enabled: Boolean = true,
) {
    Row(
        modifier = Modifier.padding(all = MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            enabled = enabled,
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(Color.Blue),
        )
        Text(text = checkBoxLabel)
    }
}

@Preview
@Composable
private fun LabelledCheckboxPreview() {
    LabelledCheckbox(
        checked = true,
        checkBoxLabel = "Blur",
        onCheckedChange = {},
    )
}
