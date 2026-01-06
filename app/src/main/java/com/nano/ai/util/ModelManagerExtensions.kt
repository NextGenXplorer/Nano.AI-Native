package com.nano.ai.util

import android.content.Context
import com.nano.ai_module.model.ModelData
import com.nano.ai_module.model.ModelType
import com.nano.ai_module.workers.ModelManager
import com.nano.ai.data.UserPrefs
import com.nano.ai_engine.models.llm_models.GGUFDatabaseModel
import com.nano.ai_engine.models.llm_models.ModelType as EngineModelType
import kotlinx.coroutines.flow.first

/**
 * Extension functions to simplify OpenRouter configuration
 * Call these from your Application class or ViewModel
 */

/**
 * Initialize ModelManager with OpenRouter credentials from SharedPreferences
 *
 * Usage in Application.onCreate():
 * ```
 * ModelManager.init(this)
 * lifecycleScope.launch {
 *     ModelManager.initOpenRouterFromPrefs(this@Application)
 * }
 * ```
 */
suspend fun initOpenRouterFromPrefs(context: Context) {
    val apiKey = UserPrefs.getOpenRouterApiKey(context).first()
    val baseUrl = UserPrefs.getOpenRouterBaseUrl(context).first()

    if (apiKey.isNotBlank()) {
        ModelManager.configureOpenRouter(apiKey, baseUrl)
    }
}

/**
 * Convert GGUFDatabaseModel (from ai-engine) to ModelData (for ai-module/app)
 * This bridges the gap between the two database schemas.
 */
fun GGUFDatabaseModel.toModelData(): ModelData {
    return ModelData(
        id = this.id,
        modelName = this.modelName,
        providerName = "GGUF",
        modelType = when (this.modelType) {
            EngineModelType.TEXT -> ModelType.TEXT
            EngineModelType.VLM -> ModelType.VLM
            EngineModelType.EMBEDDING -> ModelType.EMBEDDING
            EngineModelType.TTS -> ModelType.TTS
            EngineModelType.STT -> ModelType.STT
            else -> ModelType.TEXT
        },
        modelPath = this.modelPath,
        architecture = this.architecture.name,
        threads = this.threads,
        gpuLayers = this.gpuLayers,
        useMMAP = this.useMMAP,
        useMLOCK = this.useMLOCK,
        ctxSize = this.ctxSize,
        temp = this.temp,
        topK = this.topK,
        topP = this.topP,
        minP = this.minP,
        maxTokens = this.maxTokens,
        mirostat = this.mirostat,
        mirostatTau = this.mirostatTau,
        mirostatEta = this.mirostatEta,
        seed = this.seed,
        isImported = this.isImported,
        modelUrl = null,
        isToolCalling = false,
        systemPrompt = this.systemPrompt,
        chatTemplate = this.chatTemplate.ifEmpty { null }
    )
}

/**
 * Sync all GGUF models from ai-engine database to ai-module database.
 * Call this after model installation to make models visible in chat screen.
 */
suspend fun syncGGUFModelsToAppDatabase() {
    val ggufModels = com.nano.ai_engine.workers.installer.ModelInstaller.getInstalledGGUFModels()
    for (ggufModel in ggufModels) {
        val modelData = ggufModel.toModelData()
        // Check if already exists
        val existing = ModelManager.getModel(modelData.modelName)
        if (existing == null) {
            ModelManager.addModel(modelData)
        }
    }
}