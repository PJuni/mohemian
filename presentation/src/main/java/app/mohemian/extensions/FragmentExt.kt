package app.mohemian.extensions

import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment

val doNothing = Unit

fun Fragment.color(@ColorRes id: Int) = requireContext().getColor(id)