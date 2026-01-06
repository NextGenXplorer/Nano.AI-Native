-keep class com.nano.ai.plugin_api.theme.ThemeKt { *; }
-keepclassmembers class ** {
    @androidx.compose.runtime.Composable *;
}
# Explicitly keep interface and method names
-keep interface com.nano.ai.plugin_api.api.ComposePlugin { *; }

# Keep all method names in classes implementing ComposePlugin
-keepclassmembers class * implements com.nano.ai.plugin_api.api.ComposePlugin {
    @androidx.compose.runtime.Composable <methods>;
    public *** content(...);
}

-keep class * extends com.nano.ai.plugin_api.api.PluginApi { *; }
