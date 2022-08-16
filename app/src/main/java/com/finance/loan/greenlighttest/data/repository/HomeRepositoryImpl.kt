package com.finance.loan.greenlighttest.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.finance.loan.greenlighttest.data.db.dao.PlacesDao
import com.finance.loan.greenlighttest.data.home.remote.api.HomeApi
import com.finance.loan.greenlighttest.domain.common.base.BaseResult
import com.finance.loan.greenlighttest.domain.mainhome.MainRepository
import com.finance.loan.greenlighttest.domain.mainhome.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi,
    private val placesDao: PlacesDao
) : MainRepository {

    override suspend fun getData(): Flow<BaseResult<Place>> {

        return flow {


            val response = homeApi.getdata()

            try {

                val body = response.body()
                if (response.isSuccessful && body != null) {

                    Log.e("CheckingResponse", body.ResponseData.sales_area.size.toString())

                    // Save Country into the persistence storage
                    body.ResponseData.sales_country.let { upsertCountry(it) }

                    // Save Region into the persistence storage
                    body.ResponseData.sales_region.let { upsertRegion(it) }

                    // Save Zone into the persistence storage
                    body.ResponseData.sales_zone.let { upsertZone(it) }

                    // Save Area into the persistence storage
                    body.ResponseData.sales_area.let { upsertArea(it) }

                    if (response.errorBody() != null) {
                        val ErrorMessage = response.errorBody().toString()
                        emit(BaseResult.Success(body))
                        Log.e("CheckingErrorResponse", body.ResponseStatus.toString())
                        emit(BaseResult.Error(ErrorMessage))
                    }

                    Log.d("CheckDBSize", getAreaList().size.toString())
                    // Log.d("CheckDB",getAllArea().sales_area.size.toString())

                    emit(BaseResult.Success(body))

                }

            } catch (e: HttpException) {
                Log.d("HTTPException",e.toString())
                emit(BaseResult.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                Log.d("IOException",e.toString())
                emit(BaseResult.Error(e.localizedMessage ?: "Check out Internet Connection"))
            } catch (e: Exception) {
                Log.e("CheckingApiCallErr", e.printStackTrace().toString())
                emit(BaseResult.Error(e.localizedMessage ?: "General Exception On Api Calling"))
            }


        }
    }

    suspend fun upsertArea(area: List<SalesArea>) = placesDao.addArea(area)
    suspend fun upsertCountry(country: List<SalesCountry>) = placesDao.addCountry(country)
    suspend fun upsertRegion(region: List<SalesRegion>) = placesDao.addRegion(region)
    suspend fun upsertZone(zone: List<SalesZone>) = placesDao.addZone(zone)


    override suspend fun getAllCountry(): ArrayList<SalesCountry> {
        return ArrayList(placesDao.getAllCountry())
    }

    override suspend fun getRegionDB(): ArrayList<SalesRegion> {
        return ArrayList(placesDao.getAllRegion())
    }

    override suspend fun getAllZone(): ArrayList<SalesZone> {
        return ArrayList(placesDao.getAllZone())
    }

    override suspend fun getAreaList(): ArrayList<SalesArea> {
        return ArrayList(placesDao.getAllArea())
    }


}