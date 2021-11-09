package app.mohemian.domain.useCase

import app.mohemian.domain.datasource.FlickrDataSource
import app.mohemian.domain.entity.FlickrPhotoResponse
import app.mohemian.domain.result.RepositoryResult
import app.mohemian.domain.useCase.base.UseCaseHandler
import app.mohemian.domain.useCase.base.UseCaseParam
import javax.inject.Inject

class FetchPhotosUseCase @Inject constructor(
    private val flickrDataSource: FlickrDataSource
) : UseCaseHandler<UseCaseParam.Flickr.SearchPhoto, FlickrPhotoResponse> {

    override suspend fun execute(param: UseCaseParam.Flickr.SearchPhoto): RepositoryResult<FlickrPhotoResponse> =
        flickrDataSource.searchPhotos(param.searchText)
}