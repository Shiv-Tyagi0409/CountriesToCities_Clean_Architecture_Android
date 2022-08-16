package com.finance.loan.greenlighttest.domain.mainhome

import com.finance.loan.greenlighttest.domain.common.base.BaseResult
import com.finance.loan.greenlighttest.domain.mainhome.model.*
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getData(): Flow<BaseResult<Place>>

    suspend fun getAllCountry(): ArrayList<SalesCountry>

    suspend fun getRegionDB(): ArrayList<SalesRegion>

    suspend fun getAllZone(): ArrayList<SalesZone>

    suspend fun getAreaList(): ArrayList<SalesArea>

}