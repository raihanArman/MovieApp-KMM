package com.randev.core

/**
 * @author Raihan Arman
 * @date 29/08/22
 */

sealed class UIComponentType{

    object Dialog: UIComponentType()

    object None: UIComponentType()
}