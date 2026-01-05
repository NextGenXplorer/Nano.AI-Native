# 🔨 Building Nano.ai APK

This guide provides multiple methods to build the Nano.ai Android application.

## 🚀 Quick Start - Google Colab (Recommended)

**Easiest method - No setup required!**

1. Open the Colab notebook: [Build_Nano_AI.ipynb](./Build_Nano_AI.ipynb)
2. Click "Open in Colab" button
3. Run all cells (Runtime → Run all)
4. Wait 10-15 minutes
5. Download the APK when complete

**Advantages:**
- ✅ No local setup needed
- ✅ Free (uses Google's servers)
- ✅ Works on any device with a browser
- ✅ Clean build environment

---

## 🤖 GitHub Actions (Automated)

**Best for continuous integration**

### Auto-Build on Push
1. Push code to GitHub
2. Go to Actions tab
3. Wait for build to complete
4. Download APK from artifacts

### Create Release
```bash
git tag v0.0.1
git push origin v0.0.1
```
This automatically creates a GitHub release with the APK.

**Workflows:**
- `.github/workflows/build.yml` - Builds on every push
- `.github/workflows/release.yml` - Creates releases on tags

---

## 💻 Local Build

**For development and testing**

### Prerequisites
- Java 17 JDK
- Android SDK
- 8GB+ RAM recommended

### Build Commands

**Debug APK:**
```bash
./gradlew assembleDebug
```

**Release APK:**
```bash
./gradlew assembleRelease
```

**Clean build:**
```bash
./gradlew clean assembleDebug
```

**Output location:**
```
app/build/outputs/apk/debug/app-debug.apk
app/build/outputs/apk/release/app-release.apk
```

---

## 📦 Build Outputs

| Build Type | Package Name | Min SDK | Target SDK |
|------------|--------------|---------|------------|
| Debug | com.nano.ai | 31 | 36 |
| Release | com.nano.ai | 31 | 36 |

**APK Size:** ~50-100 MB (varies with included models)

---

## 🐛 Troubleshooting

### Local Build Issues

**Problem:** Gradle build fails
```bash
# Clear Gradle cache
./gradlew clean
rm -rf .gradle
./gradlew assembleDebug
```

**Problem:** SDK not found
- Set `ANDROID_HOME` environment variable
- Install Android SDK via Android Studio

**Problem:** Out of memory
```bash
# Increase Gradle memory
export GRADLE_OPTS="-Xmx4g -XX:MaxMetaspaceSize=512m"
./gradlew assembleDebug
```

### Colab Build Issues

**Problem:** Build timeout
- Runtime → Restart runtime
- Run cells again (cache will help)

**Problem:** Download fails
- Check browser's download settings
- Try downloading from Files panel (left sidebar)

### GitHub Actions Issues

**Problem:** Workflow not running
- Check if Actions are enabled in repository settings
- Verify branch name matches workflow triggers

**Problem:** Build fails in Actions
- Check workflow logs for detailed errors
- Download build-reports artifact for more info

---

## 🔐 Signing APK (Release Only)

### Generate Keystore
```bash
keytool -genkey -v -keystore nano-ai.keystore \
  -alias nano-ai -keyalg RSA -keysize 2048 -validity 10000
```

### Sign APK
```bash
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 \
  -keystore nano-ai.keystore \
  app/build/outputs/apk/release/app-release-unsigned.apk nano-ai
```

### Verify Signature
```bash
jarsigner -verify -verbose -certs \
  app/build/outputs/apk/release/app-release-unsigned.apk
```

---

## 📊 Build Performance

| Method | Time | Setup | Difficulty |
|--------|------|-------|------------|
| Google Colab | 10-15 min | None | ⭐ Easy |
| GitHub Actions | 8-12 min | Minimal | ⭐⭐ Easy |
| Local Build | 5-10 min | Complex | ⭐⭐⭐ Medium |

---

## 🎯 Recommended Workflow

**For Users:**
1. Use Google Colab for one-time builds
2. Download from GitHub Releases for stable versions

**For Developers:**
1. Use local builds for development
2. Use GitHub Actions for CI/CD
3. Use Colab for testing without local setup

---

## 📝 Notes

- Debug APKs are unsigned and can be installed directly
- Release APKs should be signed for distribution
- First build takes longer due to dependency downloads
- Subsequent builds are faster with Gradle cache

---

**Need Help?** Open an issue on [GitHub](https://github.com/NextGenXplorer/Nano.AI-Native/issues)
