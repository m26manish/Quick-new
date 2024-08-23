package com.example.musicappui.ui.theme
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun SettingsScreen(
    darkThemeSwitchState: MutableState<Boolean>,
    notificationSwitchState: MutableState<Boolean>,
    applyTheme: (Boolean) -> Unit
) {
    val lightThemeColors = LightColorPalette
    val darkThemeColors = DarkColorPalette

    // Determine the current theme based on darkThemeSwitchState
    val currentTheme = if (darkThemeSwitchState.value) darkThemeColors else lightThemeColors

    // Apply the theme dynamically based on switch state
    MyThemedApp(
        isDarkModeEnabled = darkThemeSwitchState.value,
        content = {
            MaterialTheme(
                colors = currentTheme,
                typography = CustomTypography,
                shapes = CustomShapes
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Settings") },
                            backgroundColor = MaterialTheme.colors.primarySurface,
                            contentColor = Color.White
                        )
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            Text(
                                text = "General Settings",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // Dark theme switch
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Switch(
                                    checked = darkThemeSwitchState.value,
                                    onCheckedChange = { darkThemeSwitchState.value = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = MaterialTheme.colors.secondary,
                                        checkedTrackColor = MaterialTheme.colors.secondaryVariant
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Dark Theme",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))

                            // Notification switch
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Switch(
                                    checked = notificationSwitchState.value,
                                    onCheckedChange = { isChecked ->
                                        notificationSwitchState.value = isChecked
                                        // Call a function to handle notifications here
                                    }
                                )
                                if (notificationSwitchState.value) {
                                    handleNotifications(isChecked = notificationSwitchState.value)
                                } else {
                                    // Handle notifications off state if needed
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Notifications",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun handleNotifications(isChecked: Boolean) {
    // Get the current context
    val context = LocalContext.current

    // Show a toast message
    val message = if (isChecked) "Notifications enabled" else "Notifications disabled"
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}