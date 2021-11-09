package app.mohemian.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.mohemian.R
import app.mohemian.extensions.doNothing
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Mohemian)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            mainViewModel.actionStream.getFlow().collect { action ->
                when (action) {
                    is MainViewModel.Action.SetTitle -> supportActionBar?.title = action.title
                    MainViewModel.Action.ShowTitle -> supportActionBar?.show()
                    MainViewModel.Action.SetMainTheme -> Unit
                }
            }
        }
    }

    override fun onBackPressed() = doNothing
}