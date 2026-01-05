<div align="center">

# 🧠 nano.ai

### Your Complete Offline AI Companion for Android

**Privacy-First • Powerful • Portable**

[![Platform](https://img.shields.io/badge/Platform-Android_8.0%2B-3DDC84?logo=android&logoColor=white)](https://github.com/NextGenXplorer/Nano.AI-Native)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)
[![Package](https://img.shields.io/badge/Package-com.nano.ai-orange)](https://github.com/NextGenXplorer/Nano.AI-Native)

*Run powerful AI models completely offline on your Android device. No internet required. No subscriptions. Complete privacy.*

[Download](#installation) • [Features](#features) • [Documentation](#quick-start-guide) • [Community](#support--community)

</div>

---

## 🌟 What is nano.ai?

**nano.ai** is a comprehensive AI ecosystem for Android that puts powerful AI capabilities directly in your pocket—completely offline. Chat with LLMs, generate images, use voice AI, and inject custom knowledge, all without an internet connection.

### Why nano.ai?

- **🔒 Privacy First** - Your data never leaves your device in offline mode
- **💪 Fully Featured** - Chat, images, voice, and custom knowledge in one app
- **🌐 Hybrid Mode** - Switch between offline and cloud models seamlessly
- **🆓 Free & Open** - No subscriptions, no paywalls, Apache 2.0 licensed
- **🔌 Extensible** - Plugin system for unlimited customization

---

## ✨ Features

### 🤖 AI Chat (LLMs)
- **Offline Models**: Run GGUF models (Llama, Mistral, Gemma, Phi) locally
- **Cloud Access**: Connect to 100+ models via OpenRouter (GPT-4, Claude, Gemini)
- **Smart Streaming**: Real-time token generation with context preservation
- **Conversation History**: Full chat persistence with SQLite storage

### 🎨 Image Generation
- **Stable Diffusion 1.5**: Generate images completely offline
- **Censored & Uncensored**: Choose the model that fits your needs
- **Mobile Optimized**: Runs on phones with 6GB+ RAM
- **Fast Generation**: 30-90 seconds depending on device

### 🎙️ Voice AI
- **Text-to-Speech**: 11 professional voices (American & British accents)
- **Speech-to-Text**: Offline Whisper-powered recognition
- **Zero Latency**: All processing happens on-device
- **Hands-Free**: Perfect for driving or multitasking

### 🧠 Knowledge Injection (RAG)
- **Custom Data-Packs**: Inject Wikipedia, docs, notes into AI context
- **No Retraining**: Add knowledge without model retraining
- **Dynamic Mounting**: Attach/detach knowledge bases on the fly
- **Universal Support**: Works with both local and cloud models

### 🔌 Plugin System
- **Web Search**: Real-time information retrieval
- **Web Scraper**: Extract content from any URL
- **DataHub**: Manage custom knowledge bases
- **Document Viewer**: Analyze PDFs and text files
- **Extensible**: Build your own plugins

---

## 📱 Screenshots

<div align="center">

| Chat Interface | Model Selection | Settings |
|:---:|:---:|:---:|
| Multi-modal AI conversations | 100+ models available | Complete customization |

*Screenshots coming soon*

</div>

---

## 🚀 Quick Start Guide

### Installation

#### Option 1: Direct Download
Download the latest APK from [Releases](https://github.com/NextGenXplorer/Nano.AI-Native/releases) and install on Android 8.0+ devices.

#### Option 2: Build from Source
```bash
# Clone repository
git clone https://github.com/NextGenXplorer/Nano.AI-Native.git
cd nano.ai

# Build with Gradle
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

### Setup for Offline Use

1. **Load a Chat Model**
   - Download a GGUF model from [HuggingFace](https://huggingface.co/models)
   - Recommended: `Llama-3-8B-Q4_K_M.gguf` (4.5GB)
   - Import via Settings → Local Models

2. **Enable Image Generation**
   - Download Stable Diffusion 1.5 model
   - Import via Settings → Image Models

3. **Activate Voice AI**
   - TTS voices included by default
   - Download Whisper for STT via Settings → Voice Models

### Setup for Cloud Use

1. Get API key from [OpenRouter.ai](https://openrouter.ai)
2. Enter key in Settings → API Configuration
3. Access 100+ cloud models instantly

---

## 💻 System Requirements

### Minimum (Cloud Models Only)
- Android 8.0+ (API 26)
- 4GB RAM
- 2GB storage

### Recommended (Local Models)
- Android 10+
- 6GB+ RAM (8GB preferred)
- Snapdragon 8 Gen 1 or equivalent
- 5GB+ storage

### Optimal (Everything Offline)
- Android 11+
- 12GB+ RAM
- Snapdragon 8 Gen 3 or equivalent
- 10GB+ storage

---

## 🛠️ Technical Stack

**Core Technologies:**
- **Language**: Kotlin + C++
- **UI**: Jetpack Compose
- **Local Inference**: llama.cpp (GGUF models)
- **Image Generation**: Stable Diffusion C++
- **Voice**: Sherpa-ONNX (TTS/STT)
- **Cloud API**: Retrofit + OkHttp
- **Database**: Room (SQLite)
- **Async**: Kotlin Coroutines + Flow

**Performance:**
- Quantized model support (Q4_K_M, Q5_K_S)
- Context caching
- Memory-mapped loading
- NPU acceleration (where available)

---

## 🤝 Contributing

We welcome contributions! Here's how to get started:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Priority Areas
- 🐛 Bug fixes and device compatibility
- 📚 Documentation improvements
- 🧪 Testing on various devices
- 🔌 New plugin development
- 🌍 Internationalization

---

## 📄 License

Distributed under the **Apache 2.0 License**. See [LICENSE](LICENSE) for details.

**What you can do:**
- ✅ Commercial use
- ✅ Modification
- ✅ Distribution
- ✅ Private use
- ✅ Patent use

**Requirements:**
- 📄 Include license and copyright notice
- 📝 Document changes made

---

## 🙏 Acknowledgments

nano.ai is built on the shoulders of giants:

- **[llama.cpp](https://github.com/ggerganov/llama.cpp)** - Efficient LLM inference
- **[Sherpa-ONNX](https://github.com/k2-fsa/sherpa-onnx)** - Offline speech synthesis
- **[Stable Diffusion](https://github.com/CompVis/stable-diffusion)** - Image generation
- **[OpenRouter](https://openrouter.ai)** - Unified API for cloud models
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** - Modern Android UI

---

## 💬 Support & Community

- 🐛 **[Issues](https://github.com/NextGenXplorer/Nano.AI-Native/issues)** - Report bugs or request features
- 💡 **[Discussions](https://github.com/NextGenXplorer/Nano.AI-Native/discussions)** - Ask questions and share ideas
- 📧 **Email**: [support@nano.ai](mailto:support@nano.ai)

---

## ❓ FAQ

**Q: Will this drain my battery?**  
A: Local inference is power-intensive. For extended use, keep your device plugged in. Cloud mode uses minimal battery.

**Q: How big are the model files?**  
A: GGUF models: 0.5GB-8GB. Stable Diffusion: ~2GB. Voice models: 50-500MB.

**Q: Is my data really private?**  
A: In offline mode, absolutely nothing leaves your device. Verify in our open-source code.

**Q: Can I use my own API keys?**  
A: Yes! Bring your own OpenRouter key. You control costs and usage.

**Q: Does it support iOS?**  
A: Not currently. Android only due to platform constraints.

---

<div align="center">

**Built with ❤️ by [NextGenXplorer](https://github.com/NextGenXplorer)**

*Privacy-first AI for everyone, everywhere*

If nano.ai empowers your AI journey, please ⭐ star the repository!

[Download](https://github.com/NextGenXplorer/Nano.AI-Native/releases) • [Report Bug](https://github.com/NextGenXplorer/Nano.AI-Native/issues) • [Request Feature](https://github.com/NextGenXplorer/Nano.AI-Native/issues)

---

**Powered by llama.cpp • Sherpa-ONNX • Stable Diffusion • OpenRouter • Jetpack Compose**

</div>
