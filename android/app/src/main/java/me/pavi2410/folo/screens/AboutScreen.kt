package me.pavi2410.folo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.pavi2410.folo.R

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.statusBarsPadding().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.folo_logo),
            contentDescription = "folo logo",
            modifier = Modifier
                .padding(16.dp)
                .size(128.dp)
                .clip(CircleShape)
        )
        Text(
            text = "folo",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "version v1.0.0",
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "hand-crafted with ❤️ by pavi2410",
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            text = "privacy policy",
            style = MaterialTheme.typography.body1,
        )
    }
}