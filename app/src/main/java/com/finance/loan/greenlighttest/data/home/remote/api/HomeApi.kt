package com.finance.loan.greenlighttest.data.home.remote.api

import com.finance.loan.greenlighttest.domain.mainhome.model.Place
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("286f38b4-c6c1-4348-aabc-6d396dcbc4de")
    suspend fun getdata():Response<Place>
}