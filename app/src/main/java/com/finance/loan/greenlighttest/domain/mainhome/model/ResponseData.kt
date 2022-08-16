package com.finance.loan.greenlighttest.domain.mainhome.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ResponseData")
data class ResponseData(

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val sales_area: List<SalesArea>,
    val sales_country: List<SalesCountry>,
    val sales_region: List<SalesRegion>,
    val sales_zone: List<SalesZone>
)