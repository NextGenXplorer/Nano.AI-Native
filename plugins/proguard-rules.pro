-keep class com.nano.ai.plugins.api.** { *; }
-keep class com.nano.ai.plugins.model.** { *; }
-keep class com.nano.ai.plugins.manager.PluginManager { *; }
-keepclassmembers class ** {
    @androidx.compose.runtime.Composable *;
}
