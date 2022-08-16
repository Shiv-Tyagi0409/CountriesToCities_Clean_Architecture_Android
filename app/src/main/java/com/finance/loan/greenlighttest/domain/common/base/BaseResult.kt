package com.finance.loan.greenlighttest.domain.common.base

sealed class BaseResult<out T> {

    data class Success<out R>(val data: R) : BaseResult<R>()
    data class Error(val msg:String) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}
