package app.mohemian.data.retrofit

import app.mohemian.data.BuildConfig
import app.mohemian.data.model.FlickrPhotoResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=${BuildConfig.FLICKR_API_KEY}")
    suspend fun fetchPhotosBy(
        @Query("text") text: String): FlickrPhotoResponseDTO
}