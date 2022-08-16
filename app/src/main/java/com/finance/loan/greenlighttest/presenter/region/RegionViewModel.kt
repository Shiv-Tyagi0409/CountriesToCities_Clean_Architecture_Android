package com.finance.loan.greenlighttest.presenter.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesRegion
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone
import com.finance.loan.greenlighttest.domain.mainhome.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {

    private val Region = MutableStateFlow<List<SalesRegion>>(mutableListOf())
    val mRegion: StateFlow<List<SalesRegion>> get() = Region


    fun getData(){
        viewModelScope.launch {
            Region.value = useCase.getRegion()
        }
    }
}