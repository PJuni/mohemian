package app.mohemian.domain.di

import app.mohemian.data.BuildConfig
import app.mohemian.data.retrofit.FlickrAPI
import app.mohemian.domain.datasource.FlickrDataSource
import app.mohemian.domain.datasource.FlickrDataSourceImpl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val LOGGING_INTERCEPTOR_TAG = "okhttp"
private const val TIMEOUT_ONE_MINUTE = 60L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor {
        Timber.tag(LOGGING_INTERCEPTOR_TAG).i(it)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ) = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT_ONE_MINUTE, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_ONE_MINUTE, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_ONE_MINUTE, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().serializeNulls().create()
    )

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.FLICKR_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideFlickrAPI(
        retrofit: Retrofit
    ): FlickrAPI = retrofit.create(FlickrAPI::class.java)

    @Singleton
    @Provides
    fun provideFlickrDataSource(flickrAPI: FlickrAPI): FlickrDataSource = FlickrDataSourceImpl(flickrAPI)
}