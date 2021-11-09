package app.mohemian.domain.datasource

import app.mohemian.domain.entity.FlickrPhotoResponse
import app.mohemian.domain.result.RepositoryResult

interface FlickrDataSource {

    suspend fun searchPhotos(text: String): RepositoryResult<FlickrPhotoResponse>
}