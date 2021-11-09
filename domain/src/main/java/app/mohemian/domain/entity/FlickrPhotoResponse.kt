package app.mohemian.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class FlickrPhotoResponse(
    val photosPage: PhotosPage
)

data class PhotosPage(
    val page: Int,
    val photos: List<Photo>
)

@Parcelize
data class Photo(
    val id: String?,
    val owner: String?,
    val secret: String?,
    val server: String?,
    val farm: Int?,
    val title: String?
): Parcelable