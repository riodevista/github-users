package com.goodapps.github_users.core.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourcesProvider {

    override fun getString(id: Int): String = context.getString(id)

    override fun getStringArray(
        arrayId: Int
    ): Array<String> = context.resources.getStringArray(arrayId)

    override fun getString(
        id: Int,
        vararg formatArgs: Any
    ): String = context.getString(id, *formatArgs)

    override fun getDrawable(id: Int): Drawable? = ContextCompat.getDrawable(context, id)

    override fun getColor(id: Int): Int = ContextCompat.getColor(context, id)

}
