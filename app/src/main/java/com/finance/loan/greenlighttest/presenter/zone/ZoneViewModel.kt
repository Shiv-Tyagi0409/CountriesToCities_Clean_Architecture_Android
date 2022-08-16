package com.finance.loan.greenlighttest.presenter.zone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesCountry
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone
import com.finance.loan.greenlighttest.domain.mainhome.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {

    private val Zone = MutableStateFlow<List<SalesZone>>(mutableListOf())
    val mZone: StateFlow<List<SalesZone>> get() = Zone


    fun getData(){

        viewModelScope.launch {
            Zone.value = useCase.getAllZone()
        }
    }


}