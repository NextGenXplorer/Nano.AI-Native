package com.nano.ai.data

import com.nano.ai.model.DataPack

/**
 * Provider for available data packs
 * Can be replaced with Firebase/API data later
 */
object DataPackProvider {

    /**
     * Get list of available data packs
     * This can be replaced with Firebase fetch or API call
     */
    fun getDataPacks(): List<DataPack> {
        return listOf(
            DataPack(
                name = "General Knowledge",
                description = "Comprehensive knowledge base covering science, history, geography, and more.",
                size = "150 MB",
                author = "Nano.ai Team",
                issued = "2025-01-01",
                license_text = "Free for personal use",
                license_type = "MIT",
                link = "https://example.com/datapacks/general-knowledge.vecx"
            ),
            DataPack(
                name = "Programming Reference",
                description = "Programming languages documentation including Python, JavaScript, Kotlin, and more.",
                size = "200 MB",
                author = "Nano.ai Team",
                issued = "2025-01-01",
                license_text = "Free for personal use",
                license_type = "MIT",
                link = "https://example.com/datapacks/programming-ref.vecx"
            ),
            DataPack(
                name = "Medical Encyclopedia",
                description = "Medical terminology, conditions, treatments, and drug information.",
                size = "180 MB",
                author = "Nano.ai Team",
                issued = "2025-01-01",
                license_text = "For educational purposes only",
                license_type = "CC BY-NC",
                link = "https://example.com/datapacks/medical-encyclopedia.vecx"
            ),
            DataPack(
                name = "Legal Documents",
                description = "Legal terminology, contract templates, and common legal procedures.",
                size = "120 MB",
                author = "Nano.ai Team",
                issued = "2025-01-01",
                license_text = "For reference only, not legal advice",
                license_type = "CC BY",
                link = "https://example.com/datapacks/legal-docs.vecx"
            ),
            DataPack(
                name = "Scientific Papers",
                description = "Summaries and abstracts from various scientific journals and research papers.",
                size = "250 MB",
                author = "Nano.ai Team",
                issued = "2025-01-01",
                license_text = "Open access content",
                license_type = "CC BY",
                link = "https://example.com/datapacks/scientific-papers.vecx"
            )
        )
    }
}
