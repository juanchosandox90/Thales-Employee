package com.sandoval.thalesemployee.di

import com.google.gson.GsonBuilder
import com.sandoval.thalesemployee.BuildConfig
import com.sandoval.thalesemployee.commons.BASE_URL
import com.sandoval.thalesemployee.data.remote.api.ThalesEmployeeService
import com.sandoval.thalesemployee.data.remote.repository.employee_detail.RemoteDataGetEmployeeDetailRepository
import com.sandoval.thalesemployee.data.remote.repository.employee_list.RemoteDataGetEmployeeListRepository
import com.sandoval.thalesemployee.domain.repository.employee_detail.IGetEmployeeDetailRepository
import com.sandoval.thalesemployee.domain.repository.employee_list.IGetEmployeeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl() = BASE_URL

    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ThalesEmployeeService =
        retrofit.create(ThalesEmployeeService::class.java)

    @Provides
    @Singleton
    fun providesGetEmployeeList(remoteDataGetEmployeeListRepository: RemoteDataGetEmployeeListRepository): IGetEmployeeListRepository =
        remoteDataGetEmployeeListRepository

    @Provides
    @Singleton
    fun providesGetEmployeeDetail(remoteDataGetEmployeeDetailRepository: RemoteDataGetEmployeeDetailRepository): IGetEmployeeDetailRepository =
        remoteDataGetEmployeeDetailRepository

}