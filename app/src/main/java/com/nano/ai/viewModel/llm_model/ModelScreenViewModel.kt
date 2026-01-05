package com.nano.ai.viewModel.llm_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nano.ai_engine.models.llm_models.GGUFDatabaseModel
import com.nano.ai_engine.workers.installer.ModelInstaller
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ModelScreenViewModel : ViewModel() {
    private val _ggufModels = MutableStateFlow<List<GGUFDatabaseModel>>(emptyList())
    val ggufModels = _ggufModels.asStateFlow()

    init {
        observeModels()
    }

    fun observeModels() {
        viewModelScope.launch {
            _ggufModels.value = ModelInstaller.getInstalledGGUFModels()
        }
    }

}