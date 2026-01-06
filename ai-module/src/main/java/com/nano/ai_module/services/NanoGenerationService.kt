package com.nano.ai_module.services

import com.mp.ai_core.services.GenerationService

/**
 * Wrapper service that extends the ai-core GenerationService.
 * This allows using com.nano.* package name in the manifest
 * while the actual implementation comes from the AAR.
 */
class NanoGenerationService : GenerationService()
