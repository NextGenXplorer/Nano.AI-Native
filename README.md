# Nano.Ai

An AI-native mobile platform for Android that brings powerful language models directly to your device.

![Android](https://img.shields.io/badge/Android-12%2B-green)
![Kotlin](https://img.shields.io/badge/Kotlin-2.1.21-purple)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-blue)

## Features

### Offline AI Chat
- Run large language models locally on your device
- Support for GGUF format models via Llama.cpp
- No internet connection required for inference
- Full privacy - your data never leaves your device

### Online AI (OpenRouter)
- Access 100+ cloud models through OpenRouter API
- Seamless switching between offline and online models
- Streaming responses with real-time token generation

### Text-to-Speech (TTS)
- 11 premium voices (American & British, Male & Female)
- Completely offline using Sherpa-ONNX
- Real-time audio synthesis as responses generate

### Plugin System
- Extensible architecture with dynamic plugin loading
- Tool calling support for LLM function execution
- Create custom plugins with Compose UI integration
- JSON-based plugin manifest system

### DataHub & RAG
- Vector database for knowledge injection
- Retrieval-Augmented Generation support
- Encrypted data pack loading
- Dynamic context injection for specialized tasks

### Code Canvas
- Syntax highlighting for generated code
- Live preview support
- Easy copy-to-clipboard functionality

## Architecture

```
Nano.Ai/
├── app/                  # Main Android application
├── ai-module/            # AI inference & model management
├── plugins/              # Plugin system & management
├── plugin-api/           # Plugin API definitions
├── data-hub-lib/         # Vector DB & RAG with native C++
└── libs/                 # Pre-built AAR libraries
```

## Tech Stack

- **Language**: Kotlin 2.1.21
- **UI**: Jetpack Compose with Material3
- **AI Engine**: Llama.cpp (via native AAR)
- **TTS Engine**: Sherpa-ONNX
- **Database**: Room ORM
- **Async**: Kotlin Coroutines & Flow
- **Encryption**: libsodium

## Requirements

- Android 12+ (API 31)
- arm64-v8a or x86_64 architecture
- Minimum 4GB RAM recommended for local inference

## Building

### Prerequisites
- JDK 17
- Android SDK 36
- Android NDK (for native components)

### Build Debug APK
```bash
./gradlew assembleDebug
```

### Build Release APK
```bash
./gradlew assembleRelease
```

## Project Structure

| Module | Package | Purpose |
|--------|---------|---------|
| app | `com.nano.ai` | Main application, UI, ViewModels |
| ai-module | `com.nano.ai.module` | Model management, inference services |
| plugins | `com.nano.ai.plugins` | Plugin loading & management |
| plugin-api | `com.nano.ai.plugin.api` | Base plugin classes & interfaces |
| data-hub-lib | `com.nano.ai.datahub` | Vector DB, RAG, native JNI |

## Permissions

```xml
INTERNET                 - Online model API access
POST_NOTIFICATIONS       - Service notifications
FOREGROUND_SERVICE       - Background inference
RECORD_AUDIO             - Voice input (STT)
MANAGE_EXTERNAL_STORAGE  - Model file access
```

## Contributing

Contributions are welcome! Please feel free to submit pull requests.

## License

This project is provided as-is for educational and personal use.
