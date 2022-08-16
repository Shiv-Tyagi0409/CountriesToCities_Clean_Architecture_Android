package com.finance.loan.greenlighttest.domain.mainhome.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class SalesCountry(
    val achievement: Int,
    val count_unsigned_contracts: Int,
    @PrimaryKey(autoGenerate = false)
    val country: String,
    val daily_runrate_current: Double,
    val daily_runrate_required: Double,
    val id: Int,
    val last_month_sales: Int,
    val lmsd_active_2_agents: Int,
    val lmsd_active_agents: Int,
    val lmsd_weighted_units: Int,
    val mtd_active_2_agents: Int,
    val mtd_active_agents: Int,
    val mtd_new_selling_agents: Int,
    val mtd_sales_bucket_1: Int,
    val mtd_sales_bucket_2: Int,
    val mtd_sales_bucket_3: Int,
    val mtd_sales_bucket_4: Int,
    val mtd_sales_bucket_5: Int,
    val mtd_sales_bucket_6: Int,
    val mtd_unit_sales: Int,
    val mtd_weighted_units: Double,
    val percent_agent_met: Double,
    val sales_monthly_goal: Int,
    val signed_contracts: Int,
    val territory: String,
    val today_sales: Int,
    val yesterday_sales: Double
)