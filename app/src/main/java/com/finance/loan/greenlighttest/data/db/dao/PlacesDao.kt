package com.finance.loan.greenlighttest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.finance.loan.greenlighttest.domain.mainhome.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacesDao {

    /**
     * Inserts [country] into the [Country.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param country SalesCountry
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountry(country: List<SalesCountry>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addZone(zone: List<SalesZone>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRegion(region: List<SalesRegion>)

    /**
     * Inserts [area] into the [Area.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param posts Posts
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArea(area: List<SalesArea>)


//    /**
//     * Deletes all the posts from the [Area] table.
//     */
//    @Query("DELETE FROM Area")
//    suspend fun deleteAllArea()
//
//    @Query("DELETE FROM Country")
//    suspend fun deleteAllCountry()
//
//    @Query("DELETE FROM Region")
//    suspend fun deleteAllRegion()
//
//    @Query("DELETE FROM Zone")
//    suspend fun deleteAllZone()


    /**
     * Fetches all the posts from the [Area] table.
     * @return [Flow]
     */

    @Query("SELECT * FROM Area")
    suspend fun getAllArea(): List<SalesArea>

    /**
     * Fetches all the posts from the [Country] table.
     * @return [Flow]
     */

    @Query("SELECT * FROM Country")
    suspend fun getAllCountry(): List<SalesCountry>


    /**
     * Fetches all the posts from the [Region] table.
     * @return [Flow]
     */

    @Query("SELECT * FROM Region")
    suspend fun getAllRegion(): List<SalesRegion>


    /**
     * Fetches all the posts from the [Zone] table.
     * @return [Flow]
     */

    @Query("SELECT * FROM Zone")
    suspend fun getAllZone(): List<SalesZone>


//    @Query("SELECT * FROM ResponseData")
//    suspend fun getAll(): ResponseData


//    @Query("SELECT * FROM Country")
//    suspend fun getCountryList(): List<SalesArea>
}