package com.finance.loan.greenlighttest.data.module

import android.content.Context
import androidx.room.Room
import com.finance.loan.greenlighttest.App
import com.finance.loan.greenlighttest.data.db.GreenLightDatabase
import com.finance.loan.greenlighttest.data.db.dao.PlacesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideApplicationClass(@ApplicationContext app: Context): App =
        app as App

    @Singleton
    @Provides
    fun provideBalanceDatabase(app: App) =
        Room.databaseBuilder(
            app,
            GreenLightDatabase::class.java,
            "db_greenlight"
        ).build()

    @Provides
    @Singleton
    fun providePlacesDao(db: GreenLightDatabase): PlacesDao =
        db.getPlacesDao()
}