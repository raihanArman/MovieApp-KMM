package com.randev.movieapp_kmm.android.composable.utils

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.randev.movieapp_kmm.android.utils.ContextProvider

/**
 * @author Raihan Arman
 * @date 12/10/22
 */

fun getColorIntFromRes(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(ContextProvider.instance.get(), colorResId)
}

fun Color.asDisabledColor() = this.copy(alpha = 0.7f)