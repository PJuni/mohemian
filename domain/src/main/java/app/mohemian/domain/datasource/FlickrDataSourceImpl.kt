package app.mohemian.domain.datasource

import app.mohemian.data.retrofit.FlickrAPI
import app.mohemian.domain.entity.FlickrPhotoResponse
import app.mohemian.domain.mapper.toEntity
import app.mohemian.domain.result.RepositoryResult
import app.mohemian.domain.result.safeCall
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrDataSourceImpl @Inject constructor(
    private val flickrAPI: FlickrAPI
) : FlickrDataSource {

    override suspend fun searchPhotos(text: String): RepositoryResult<FlickrPhotoResponse> = safeCall {
        Timber.i("Fetch photos by: $text")
        flickrAPI.fetchPhotosBy(text).toEntity()
    }
}