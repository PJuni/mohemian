package app.mohemian.data.model


data class FlickrPhotoResponseDTO(
    val photos: PhotosPageDTO
)

data class PhotosPageDTO(
    val page: Int,
    val photo: ArrayList<PhotoDTO>
)

data class PhotoDTO(
    val id: String?,
    val owner: String?,
    val secret: String?,
    val server: String?,
    val farm: Int?,
    val title: String?
)