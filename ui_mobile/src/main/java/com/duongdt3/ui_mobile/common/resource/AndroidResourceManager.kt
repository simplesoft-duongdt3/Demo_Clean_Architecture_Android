package com.duongdt3.ui_mobile.common.resource

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat

class AndroidResourceManager(private val context: Context) {
    fun getBoolean(booleanId: Int): Boolean = context.resources.getBoolean(booleanId)

    fun getQuantityString(@PluralsRes idStr: Int, quantity: Int): String = context.resources.getQuantityString(idStr, quantity)

    fun getString(@StringRes stringId: Int): String = context.getString(stringId)

    fun getInt(@IntegerRes intId: Int): Int = context.resources.getInteger(intId)

    fun getDrawable(@DrawableRes drawableId: Int): Drawable = ContextCompat.getDrawable(context, drawableId)!!

    @ColorInt
    fun getColor(@ColorRes colorId: Int): Int = ContextCompat.getColor(context, colorId)

    fun pxToDp(px: Int): Int = (px.toFloat() / Resources.getSystem().displayMetrics.density).toInt()

    fun dpToPx(dp: Int): Int = (dp.toFloat() * Resources.getSystem().displayMetrics.density).toInt()
}