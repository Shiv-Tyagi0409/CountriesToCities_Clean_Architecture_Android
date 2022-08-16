package com.finance.loan.greenlighttest.presenter.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesArea
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesRegion
import com.finance.loan.greenlighttest.domain.mainhome.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {

    private val AreaList = MutableStateFlow<List<SalesArea>>(mutableListOf())
    val mArea: StateFlow<List<SalesArea>> get() = AreaList


    fun getData(){
        viewModelScope.launch {
            AreaList.value = useCase.getAllArea()
        }
    }
}