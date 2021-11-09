package app.mohemian.extensions

import android.content.Context
import android.text.InputType
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

const val INPUT_ONLY_DIGITS = InputType.TYPE_CLASS_NUMBER or
        InputType.TYPE_NUMBER_FLAG_DECIMAL or
        InputType.TYPE_NUMBER_FLAG_SIGNED

@ColorInt
fun View.color(@ColorRes id: Int) = ContextCompat.getColor(context, id)

fun View.dpToPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    resources.displayMetrics
).roundToInt()


fun View.dpFromPx(px: Float): Float = px / context.resources.displayMetrics.density

fun View.showKeyboard(flag: Int = InputMethodManager.SHOW_IMPLICIT) {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, flag)
}

fun View.hideKeyboard(flags: Int = 0) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, flags)
}