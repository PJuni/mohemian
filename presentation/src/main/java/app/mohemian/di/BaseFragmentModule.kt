package app.mohemian.di

import androidx.fragment.app.Fragment
import app.mohemian.ui.base.BaseFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object BaseFragmentModule {

    @Provides
    fun provideBaseFragment(fragment: Fragment): BaseFragment {
        check(fragment is BaseFragment) { "Fragment should extend BaseFragment" }
        return fragment
    }
}