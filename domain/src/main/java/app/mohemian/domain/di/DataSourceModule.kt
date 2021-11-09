package app.mohemian.domain.di

import app.mohemian.domain.datasource.FlickrDataSource
import app.mohemian.domain.datasource.FlickrDataSourceImpl
import dagger.hilt.InstallIn
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindFlickrDataSource(flickrDataSourceImpl: FlickrDataSourceImpl): FlickrDataSource
}