package app.mohemian.util

import app.mohemian.domain.entity.Photo

enum class FlickrImageSize(val size: String) {
    SMALL("q"),
    ORIGINAL("o")
// didnt manage to display original size even once -> doc says might be restricted by the user, wrong file format or something else.. didnt have much time on that TBH
}

object FlickrUtil {

    fun buildUrl(photo: Photo, imageSize: FlickrImageSize) =
        "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_${imageSize.size}.jpg"
}