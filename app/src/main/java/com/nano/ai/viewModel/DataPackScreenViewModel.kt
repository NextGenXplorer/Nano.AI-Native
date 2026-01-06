package com.nano.ai.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nano.ai_module.workers.downloadFile
import com.nano.ai.BuildConfig
import com.nano.ai.data.DataPackProvider
import com.nano.ai.model.DataPack
import com.nano.ai.worker.DataHubManager
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

data class DataPackUiState(
    val dataPack: DataPack,
    val progress: Float = 0f, // 0.0 → 1.0
    val isDownloading: Boolean = false,
    val isInstalled: Boolean = false
)

class DataPackScreenViewModel : ViewModel() {

    private val _packs = MutableStateFlow<List<DataPackUiState>>(emptyList())
    val packs = _packs.asStateFlow()

    fun loadDataPacks() {
        Log.d("DataPackScreenViewModel", "Installed datasets: ${DataHubManager.installedDataSets.value}")

        viewModelScope.launch {
            try {
                // Get available data packs from provider
                val availablePacks = DataPackProvider.getDataPacks()

                // Get installed datasets to check installation status
                val installedDataSets = DataHubManager.installedDataSets.value

                // Map to UI state with installation status
                val packStates = availablePacks.map { dataPack ->
                    val isInstalled = installedDataSets.any {
                        it.modelName == dataPack.name ||
                        it.modelName == dataPack.name.replace(" ", "-")
                    }
                    DataPackUiState(
                        dataPack = dataPack,
                        progress = if (isInstalled) 1f else 0f,
                        isDownloading = false,
                        isInstalled = isInstalled
                    )
                }

                _packs.value = packStates
                Log.d("DataPackScreenViewModel", "Loaded ${packStates.size} data packs, ${packStates.count { it.isInstalled }} installed")
            } catch (e: Exception) {
                Log.e("DataPackScreenViewModel", "Error loading data packs", e)
                _packs.value = emptyList()
            }
        }
    }

    private fun isDownloaded(dataPack: DataPack): Boolean {
        return DataHubManager.installedDataSets.value.find {
            it.modelName == dataPack.name
        } != null
    }

    fun installDataPack(dataPack: DataPack, context: Context) {
        updateUiState(dataPack) { it.copy(isDownloading = true, progress = 0f) }

        val file = File(context.filesDir, "data-packs/${dataPack.name.replace(" ","-")}.vecx")
        file.parentFile?.mkdirs()

        viewModelScope.launch {
            downloadFile(
                fileUrl = dataPack.link,
                outputFile = file,
                onProgress = { progress ->
                    updateUiState(dataPack) { it.copy(progress = progress) }
                },
                onComplete = {
                    DataHubManager.installPack(file, password = BuildConfig.ALIAS) { success ->
                        if (success) {
                            updateUiState(dataPack) {
                                it.copy(
                                    isDownloading = false,
                                    isInstalled = true,
                                    progress = 1f
                                )
                            }
                        } else {
                            updateUiState(dataPack) {
                                it.copy(
                                    isDownloading = false,
                                    isInstalled = false,
                                    progress = 0f
                                )
                            }
                        }
                    }
                },
                onError = { e ->
                    Log.e("DataPackDL", "Download failed", e)
                    updateUiState(dataPack) { it.copy(isDownloading = false, progress = 0f) }
                }
            )
        }
    }

    fun deleteDataPack(dataPack: DataPack, context: Context) {
        val file = File(context.filesDir, "data-packs/${dataPack.name}.vecx")
        if (file.exists()) file.delete()

        DataHubManager.uninstallPack(dataPack.name)

        updateUiState(dataPack) {
            it.copy(
                isInstalled = false,
                progress = 0f,
                isDownloading = false
            )
        }
    }

    private fun updateUiState(dataPack: DataPack, update: (DataPackUiState) -> DataPackUiState) {
        _packs.value = _packs.value.map {
            if (it.dataPack.name == dataPack.name) update(it) else it
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
