package com.goodapps.github_users.core.resources

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import javax.inject.Singleton

@Singleton
interface ResourcesProvider {

    fun getString(@StringRes id: Int): String

    fun getStringArray(@ArrayRes arrayId: Int): Array<String>

    fun getString(@StringRes id: Int, vararg formatArgs: Any): String

    fun getDrawable(@DrawableRes id: Int): Drawable?

    @ColorInt
    fun getColor(@ColorRes id: Int): Int

}
