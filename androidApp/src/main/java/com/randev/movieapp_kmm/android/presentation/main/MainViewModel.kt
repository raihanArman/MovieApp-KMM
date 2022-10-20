package com.randev.movieapp_kmm.android.presentation.main

import androidx.lifecycle.ViewModel
import com.randev.navigation.AppNavigator

/**
 * @author Raihan Arman
 * @date 19/10/22
 */

class MainViewModel(
    private val appNavigator: AppNavigator
): ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

}