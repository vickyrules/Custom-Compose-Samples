package com.material3.rally.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.material3.rally.R

// Set of Material typography styles to start with
private val EczarFontFamily = FontFamily(
    Font(R.font.eczar_regular),
    Font(R.font.eczar_semibold, FontWeight.SemiBold)
)

private val RobotoCondensedFontFamily = FontFamily(
    Font(R.font.robotocondensed_regular),
    Font(R.font.robotocondensed_light, FontWeight.Light),
    Font(R.font.robotocondensed_bold, FontWeight.Bold),
)



val Typography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.W100,
        fontSize = 96.sp,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 44.sp,
        fontFamily = EczarFontFamily,
        letterSpacing = 1.5.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
     titleLarge =  TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 3.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.em
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.1.em
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.em
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.em
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 10.sp
    )
)
