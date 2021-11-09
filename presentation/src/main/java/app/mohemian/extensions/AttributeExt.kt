package app.mohemian.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

fun AttributeSet.obtainAttributes(
    context: Context,
    styleable: IntArray
): TypedArray = context.theme.obtainStyledAttributes(
    this,
    styleable,
    0,
    0
)