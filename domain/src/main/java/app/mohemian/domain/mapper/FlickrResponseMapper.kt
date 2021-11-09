package app.mohemian.domain.mapper

import app.mohemian.data.model.FlickrPhotoResponseDTO
import app.mohemian.data.model.PhotoDTO
import app.mohemian.data.model.PhotosPageDTO
import app.mohemian.domain.entity.FlickrPhotoResponse
import app.mohemian.domain.entity.Photo
import app.mohemian.domain.entity.PhotosPage

fun FlickrPhotoResponseDTO.toEntity() = FlickrPhotoResponse(
    photosPage = photos.toEntity()
)

fun PhotosPageDTO.toEntity() = PhotosPage(
    page = page,
    photos = photo.map { it.toEntity() }
)

fun PhotoDTO.toEntity() = Photo(
    id,
    owner,
    secret,
    server,
    farm,
    title
)