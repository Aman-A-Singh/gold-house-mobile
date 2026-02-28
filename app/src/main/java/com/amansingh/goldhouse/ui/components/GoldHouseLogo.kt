package com.amansingh.goldhouse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amansingh.goldhouse.R
import com.amansingh.goldhouse.ui.theme.GoldGradient
import com.amansingh.goldhouse.ui.theme.GoldHouseTheme

@Composable
fun GoldHouseLogo() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(GoldGradient),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.gold_house_logo_text),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
     }
}

@Preview
@Composable
private fun LogoPreview() {
    GoldHouseTheme() {
        GoldHouseLogo()
    }
}