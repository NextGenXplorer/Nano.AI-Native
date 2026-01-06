package com.nano.ai.datahub.model

import kotlinx.serialization.Serializable

@Serializable
data class RagResult(
    val docs: List<Doc>,
    val stats: GenerationStats
)