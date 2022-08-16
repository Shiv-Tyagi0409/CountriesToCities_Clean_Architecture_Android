package com.finance.loan.greenlighttest.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.loan.greenlighttest.domain.common.base.BaseResult
import com.finance.loan.greenlighttest.domain.mainhome.model.Place
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesCountry
import com.finance.loan.greenlighttest.domain.mainhome.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {

    private val state = MutableStateFlow<HomeMainFragmentState>(HomeMainFragmentState.Init)
    val mState: StateFlow<HomeMainFragmentState> get() = state

    private val Country = MutableStateFlow<List<SalesCountry>>(mutableListOf())
    val mCountry: StateFlow<List<SalesCountry>> get() = Country

    private fun setLoading() {
        state.value = HomeMainFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = HomeMainFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = HomeMainFragmentState.ShowToast(message)
    }


    fun getData() {
        viewModelScope.launch {
            useCase.execute()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    Log.d("ErrorCatch", exception.message.toString())
                    showToast(exception.message.toString())

                    delay(2000)
                    state.value = HomeMainFragmentState.ErrorLogin("No Internet "+exception.message.toString())
                    showToast("Getting Recent Data From DB")
                    Country.value= useCase.getCountry()
                }
                .collect { result ->
                    hideLoading()

                    when (result) {
                        is BaseResult.Error -> {
                            Log.d("ResultError",result.msg)
                            state.value = HomeMainFragmentState.ErrorLogin(result.msg)
                        }
                        is BaseResult.Success -> {
                            state.value = HomeMainFragmentState.SuccessLogin(result.data)
                        }

                        BaseResult.Loading -> TODO()
                    }
                }
        }

    }

    fun getZone() {
        viewModelScope.launch {
            useCase.getAllZone()
        }
    }

}


sealed class HomeMainFragmentState {
    object Init : HomeMainFragmentState()
    data class IsLoading(val isLoading: Boolean) : HomeMainFragmentState()
    data class ShowToast(val message: String) : HomeMainFragmentState()
    data class SuccessLogin(val loginResponse: Place) : HomeMainFragmentState()
    data class ErrorLogin(val rawResponse: String) : HomeMainFragmentState()
}