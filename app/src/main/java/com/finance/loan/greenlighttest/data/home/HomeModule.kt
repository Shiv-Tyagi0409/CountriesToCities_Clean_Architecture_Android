package com.finance.loan.greenlighttest.data.home

import com.finance.loan.greenlighttest.data.db.dao.PlacesDao
import com.finance.loan.greenlighttest.data.home.remote.api.HomeApi
import com.finance.loan.greenlighttest.data.module.NetworkModule
import com.finance.loan.greenlighttest.data.repository.HomeRepositoryImpl
import com.finance.loan.greenlighttest.domain.mainhome.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit) : HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(homeApi: HomeApi,placesDao: PlacesDao) : MainRepository {
        return HomeRepositoryImpl(homeApi,placesDao)
    }
}