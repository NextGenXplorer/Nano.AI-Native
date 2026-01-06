package com.nano.ai_module.model

import android.content.Intent
import android.os.Bundle
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import org.json.JSONObject
import java.util.UUID


@Entity(tableName = "local_models")
data class ModelData(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    // Basic info
    var modelName: String = "",
    var providerName: String = "",
    var modelType: ModelType = ModelType.TEXT,
    var modelPath: String = "",
    var architecture: String = "",

    // Performance settings
    var threads: Int = (Runtime.getRuntime().availableProcessors().coerceAtLeast(2)) / 2,
    var gpuLayers: Int = 0,
    var useMMAP: Boolean = true,
    var useMLOCK: Boolean = false,
    var ctxSize: Int = 4_048,

    // Sampling settings
    var temp: Float = 0.7f,
    var topK: Int = 20,
    var topP: Float = 0.5f,
    var minP: Float = 0.0f,
    var maxTokens: Int = 2048,

    // Text behavior tuning
    var mirostat: Int = 1,                  // 0=off, 1=v1, 2=v2 (adaptive sampling)
    var mirostatTau: Float = 5.0f,          // target entropy
    var mirostatEta: Float = 0.1f,          // learning rate for mirostat

    // Misc control
    var seed: Int = -1,                     // -1=random, else fixed generation
    var isImported: Boolean = false,
    var modelUrl: String? = null,
    var isToolCalling: Boolean = false,

    // Prompt configuration
    var systemPrompt: String = "You are a helpful assistant.",
    var chatTemplate: String? = null
) {
    companion object
}


@Serializable
data class OpenRouterModel(
    val id: String,
    val name: String,
    val ctxSize: Int,
    val temperature: Float,
    val topP: Float,
    val supportsTools: Boolean = false
)

fun OpenRouterModel.toModelData(): ModelData {
    return ModelData(
        id = id,
        modelName = name,
        providerName = "",
        modelUrl = id,
        ctxSize = ctxSize,
        temp = temperature,
        topP = topP,
        isToolCalling = supportsTools
    )
}

sealed class LoadState {
    object Idle : LoadState()
    data class Loading(val progress: Float) : LoadState()
    data class OnLoaded(val model: ModelData) : LoadState()
    data class Error(val message: String) : LoadState()
}

data class GenerationParams(val maxTokens: Int = 2048)



enum class ModelType {
    TEXT,
    TTS,
    STT,
    VLM,
    EMBEDDING
}

/**
 * Converts ModelData to JSON string for storage or transfer.
 */
fun ModelData.toJson(): String {
    return JSONObject().apply {
        put("id", id)
        put("modelName", modelName)
        put("providerName", providerName)
        put("modelType", modelType.name)
        put("modelPath", modelPath)
        put("architecture", architecture)
        put("threads", threads)
        put("gpuLayers", gpuLayers)
        put("useMMAP", useMMAP)
        put("useMLOCK", useMLOCK)
        put("ctxSize", ctxSize)
        put("temp", temp)
        put("topK", topK)
        put("topP", topP)
        put("minP", minP)
        put("maxTokens", maxTokens)
        put("mirostat", mirostat)
        put("mirostatTau", mirostatTau)
        put("mirostatEta", mirostatEta)
        put("seed", seed)
        put("isImported", isImported)
        put("modelUrl", modelUrl)
        put("isToolCalling", isToolCalling)
        put("systemPrompt", systemPrompt)
        put("chatTemplate", chatTemplate)
    }.toString()
}

/* ========================================================================= */
/* UTILITY EXTENSIONS                                                        */
/* ========================================================================= */

/**
 * Creates a copy of ModelData with minimal fields for download purposes.
 */
fun ModelData.toDownloadModel(): ModelData {
    return copy(
        threads = 4,
        gpuLayers = 0,
        useMMAP = true,
        useMLOCK = false
    )
}

/**
 * Gets formatted model size if available from the path.
 */
fun ModelData.getFormattedSize(): String? {
    if (modelPath.isBlank()) return null

    return try {
        val file = java.io.File(modelPath)
        if (file.exists()) {
            val bytes = file.length()
            when {
                bytes < 1024 -> "$bytes B"
                bytes < 1024 * 1024 -> "%.2f KB".format(bytes / 1024.0)
                bytes < 1024 * 1024 * 1024 -> "%.2f MB".format(bytes / (1024.0 * 1024))
                else -> "%.2f GB".format(bytes / (1024.0 * 1024 * 1024))
            }
        } else null
    } catch (e: Exception) {
        null
    }
}

/**
 * Creates ModelData from JSON string for deserialization.
 */
fun ModelData.Companion.fromJson(jsonString: String): ModelData {
    val json = JSONObject(jsonString)
    return ModelData(
        id = json.optString("id", java.util.UUID.randomUUID().toString()),
        modelName = json.optString("modelName", ""),
        providerName = json.optString("providerName", ""),
        modelType = try { ModelType.valueOf(json.optString("modelType", "TEXT")) } catch (e: Exception) { ModelType.TEXT },
        modelPath = json.optString("modelPath", ""),
        architecture = json.optString("architecture", ""),
        threads = json.optInt("threads", (Runtime.getRuntime().availableProcessors().coerceAtLeast(2)) / 2),
        gpuLayers = json.optInt("gpuLayers", 0),
        useMMAP = json.optBoolean("useMMAP", true),
        useMLOCK = json.optBoolean("useMLOCK", false),
        ctxSize = json.optInt("ctxSize", 4096),
        temp = json.optDouble("temp", 0.7).toFloat(),
        topK = json.optInt("topK", 20),
        topP = json.optDouble("topP", 0.5).toFloat(),
        minP = json.optDouble("minP", 0.0).toFloat(),
        maxTokens = json.optInt("maxTokens", 2048),
        mirostat = json.optInt("mirostat", 1),
        mirostatTau = json.optDouble("mirostatTau", 5.0).toFloat(),
        mirostatEta = json.optDouble("mirostatEta", 0.1).toFloat(),
        seed = json.optInt("seed", -1),
        isImported = json.optBoolean("isImported", false),
        modelUrl = json.optString("modelUrl", "").takeIf { it.isNotEmpty() },
        isToolCalling = json.optBoolean("isToolCalling", false),
        systemPrompt = json.optString("systemPrompt", "You are a helpful assistant."),
        chatTemplate = json.optString("chatTemplate", "").takeIf { it.isNotEmpty() }
    )
}