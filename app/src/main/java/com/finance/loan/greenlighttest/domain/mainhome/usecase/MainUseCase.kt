package com.finance.loan.greenlighttest.domain.mainhome.usecase

import com.finance.loan.greenlighttest.domain.common.base.BaseResult
import com.finance.loan.greenlighttest.domain.mainhome.MainRepository
import com.finance.loan.greenlighttest.domain.mainhome.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend fun execute(): Flow<BaseResult<Place>> {
        return mainRepository.getData()
    }

    suspend fun getCountry(): ArrayList<SalesCountry> {
        return mainRepository.getAllCountry()
    }

    suspend fun getRegion(): ArrayList<SalesRegion> {
        return mainRepository.getRegionDB()
    }

    suspend fun getAllZone(): ArrayList<SalesZone> {
        return mainRepository.getAllZone()
    }

    suspend fun getAllArea(): ArrayList<SalesArea> {
        return mainRepository.getAreaList()
    }








}