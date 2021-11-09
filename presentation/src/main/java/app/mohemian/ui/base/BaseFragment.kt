package app.mohemian.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import app.mohemian.ui.activity.MainActivity
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    internal val mainViewModel by lazy {
        (activity as MainActivity).mainViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        bindData()
    }

    abstract fun bindView()
    abstract fun bindData()
}