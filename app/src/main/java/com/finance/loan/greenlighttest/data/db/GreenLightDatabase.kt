package com.finance.loan.greenlighttest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.finance.loan.greenlighttest.data.db.dao.PlacesDao
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesArea
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesCountry
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesRegion
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone

@Database(entities = [SalesArea::class,SalesCountry::class, SalesRegion::class, SalesZone::class], version = 1, exportSchema = false)
abstract class GreenLightDatabase : RoomDatabase(){

    abstract fun getPlacesDao(): PlacesDao
}