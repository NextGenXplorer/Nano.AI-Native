# GitHub Actions Workflows

This directory contains automated CI/CD workflows for building the Nano.ai Android app.

## Workflows

### 1. Build APK (`build.yml`)
**Triggers:**
- Push to `main`, `master`, or `develop` branches
- Pull requests to these branches
- Manual trigger via GitHub UI

**What it does:**
- Builds debug APK automatically
- Uploads APK as artifact (available for 30 days)
- Uploads build reports if build fails

**How to download the APK:**
1. Go to the Actions tab in your GitHub repository
2. Click on the latest successful workflow run
3. Download the `nano-ai-debug` artifact
4. Extract the ZIP file to get the APK

### 2. Release APK (`release.yml`)
**Triggers:**
- Push tags starting with `v` (e.g., `v0.0.1`, `v1.0.0`)
- Manual trigger via GitHub UI

**What it does:**
- Builds release APK
- Signs the APK (if secrets are configured)
- Creates GitHub Release with APK attached
- Uploads APK as artifact (available for 90 days)

**How to create a release:**
```bash
git tag v0.0.1
git push origin v0.0.1
```

## Setup Instructions

### For Unsigned Builds
No setup required! The workflows will work immediately after pushing to GitHub.

### For Signed Releases (Optional)
To enable APK signing for releases, add these secrets to your GitHub repository:

1. Go to Settings → Secrets and variables → Actions
2. Add the following secrets:
   - `SIGNING_KEY`: Base64-encoded keystore file
   - `ALIAS`: Keystore alias
   - `KEY_STORE_PASSWORD`: Keystore password
   - `KEY_PASSWORD`: Key password

**To create a base64-encoded keystore:**
```bash
# On Linux/Mac
base64 -i your-keystore.jks | pbcopy

# On Windows (PowerShell)
[Convert]::ToBase64String([IO.File]::ReadAllBytes("your-keystore.jks")) | Set-Clipboard
```

## Requirements

- Java 17 (automatically installed by workflow)
- Gradle wrapper (included in repository)
- Android SDK (automatically installed by workflow)

## Troubleshooting

If the build fails:
1. Check the workflow logs in the Actions tab
2. Download the `build-reports` artifact for detailed error information
3. The workflow uses `--stacktrace` for detailed error output

## Notes

- Debug builds are unsigned and can be installed directly
- Release builds require signing for distribution
- APK artifacts are automatically deleted after retention period
- Workflows run on Ubuntu latest (Linux environment)
