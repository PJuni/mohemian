package app.mohemian.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateView(@LayoutRes view: Int): View = LayoutInflater.from(context).inflate(
    view,
    this,
    false
)